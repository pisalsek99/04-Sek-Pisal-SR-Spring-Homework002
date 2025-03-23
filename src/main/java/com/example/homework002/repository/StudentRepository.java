package com.example.homework002.repository;
import org.apache.ibatis.annotations.*;
import com.example.homework002.model.Course;
import com.example.homework002.model.Student;
import com.example.homework002.model.request.StudentCreateRequest;
import com.example.homework002.model.request.StudentUpdateRequest;
import java.util.List;

@Mapper
public interface StudentRepository {
    @Select("""
        SELECT * FROM students 
        LIMIT #{size} OFFSET ((#{page} - 1) * #{size})
    """)
    @Results(id = "studentMapper", value = {
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "courses", column = "student_id",
                    many = @Many(select = "com.example.homework002.repository.StudentRepository.findCoursesByStudentId")
            )
    })
    List<Student> findAllStudents(@Param("page") Integer page, @Param("size") Integer size);

    @Select("""
        SELECT c.* FROM courses c
        INNER JOIN student_course sc ON c.course_id = sc.course_id
        WHERE sc.student_id = #{studentId}
    """)
    @Results(id = "courseMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "instructor", column = "instructor_id",
                    one = @One(select = "com.example.homework002.repository.InstructorRepository.findInstructorById")
            )
    })
    List<Course> findCoursesByStudentId(@Param("studentId") Integer studentId);

    @Select("""
        SELECT * FROM students
        WHERE student_id = #{studentId}
    """)
    @ResultMap("studentMapper")
    Student findStudentById(@Param("studentId") Integer studentId);

    @Insert("""
        INSERT INTO students (student_name, email, phone_number)
        VALUES (#{student.studentName}, #{student.email}, #{student.phoneNumber})
        RETURNING student_id
    """)
    Integer createStudent(@Param("student") StudentCreateRequest studentCreateRequest);


    @Insert("""
        INSERT INTO student_course(student_id, course_id)
        VALUES (#{studentId}, #{courseId})
    """)
    void createStudentCourse(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);


    @Delete("DELETE FROM students WHERE student_id = #{studentId}")
    void deleteStudentById(@Param("studentId") Integer studentId);

    @Update("""
        UPDATE students
        SET student_name = #{student.studentName}, email = #{student.email}, phone_number = #{student.phoneNumber}
        WHERE student_id = #{studentId} RETURNING student_id
    """)
    Integer updateStudentById(@Param("studentId") Integer id, @Param("student") StudentUpdateRequest studentUpdateRequest);

    @Insert("""
        INSERT INTO student_course(student_id, course_id)
        VALUES (#{studentId}, #{courseId})
    """)
    void updateStudentCourse(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);


    @Delete("""
        DELETE FROM student_course WHERE student_id = #{studentId}
    """)
    void deleteStudentCourse(@Param("studentId") Integer studentId);
}