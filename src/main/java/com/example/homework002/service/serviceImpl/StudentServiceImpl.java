package com.example.homework002.service.serviceImpl;

import org.apache.ibatis.annotations.Param;
import com.example.homework002.model.Course;
import com.example.homework002.model.Student;
import com.example.homework002.model.request.StudentCreateRequest;
import com.example.homework002.model.request.StudentUpdateRequest;
import com.example.homework002.repository.CourseRepository;
import com.example.homework002.repository.StudentRepository;
import com.example.homework002.service.StudentService;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Student> findAllStudents(Integer page, Integer size) {
        return studentRepository.findAllStudents(page, size);
    }

    @Override
    public Student findStudentById(Integer id) {
        return studentRepository.findStudentById(id);
    }

    @Override
    @Transactional
    public Student createStudent(StudentCreateRequest studentCreateRequest) {
        Integer studentId = studentRepository.createStudent(studentCreateRequest);

        for (Integer courseId : studentCreateRequest.getCourses()) {
            studentRepository.updateStudentCourse(studentId, courseId);
        }

        return studentRepository.findStudentById(studentId);
    }

    @Override
    @Transactional
    public void deleteStudentById(Integer id) {
        studentRepository.deleteStudentCourse(id);
        studentRepository.deleteStudentById(id);
    }

    @Override
    @Transactional
    public Student updateStudentById(Integer id, StudentUpdateRequest studentUpdateRequest) {
        Student student = studentRepository.findStudentById(id);
        if (student == null) {
            return null;
        }

        for (Integer courseId : studentUpdateRequest.getCourses()) {
            Course course = courseRepository.findCourseById(courseId);
            if (course == null) {
                return null;
            }
        }

        studentRepository.deleteStudentCourse(student.getStudentId());
        for (Integer courseId : studentUpdateRequest.getCourses()) {
            studentRepository.updateStudentCourse(student.getStudentId(), courseId);
        }

        studentRepository.updateStudentById(student.getStudentId(), studentUpdateRequest);
        return studentRepository.findStudentById(student.getStudentId());
    }
}
