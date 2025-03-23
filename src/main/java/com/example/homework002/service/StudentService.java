package com.example.homework002.service;

import com.example.homework002.model.Course;
import com.example.homework002.model.Student;
import com.example.homework002.model.request.CourseCreateRequest;
import com.example.homework002.model.request.StudentCreateRequest;
import com.example.homework002.model.request.StudentUpdateRequest;

import java.util.List;

public interface StudentService {
    List<Student> findAllStudents(Integer page, Integer size);
    Student findStudentById(Integer id);
    Student createStudent(StudentCreateRequest studentCreateRequest);
    void deleteStudentById(Integer id);
    Student updateStudentById(Integer id, StudentUpdateRequest studentUpdateRequest);
}
