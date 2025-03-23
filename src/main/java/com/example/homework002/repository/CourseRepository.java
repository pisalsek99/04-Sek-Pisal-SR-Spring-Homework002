package com.example.homework002.repository;

import com.example.homework002.model.Course;
import com.example.homework002.model.request.CourseCreateRequest;
import com.example.homework002.model.request.CourseUpdateRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseRepository {

    @Select("""
        SELECT * FROM courses LIMIT #{size} OFFSET #{size} * (#{page} - 1)
    """)
    @Results(id = "courseMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "description", column = "description"),
            @Result(property = "instructorId", column = "instructor_id")
    })
    List<Course> findAllCourses(@Param("page") Integer page, @Param("size") Integer size);

    @Select("SELECT * FROM courses WHERE course_id = #{courseId}")
    @ResultMap("courseMapper")
    Course findCourseById(@Param("courseId") Integer courseId);

    @Insert("""
        INSERT INTO courses (course_name, description, instructor_id) 
        VALUES (#{course.courseName}, #{course.description}, #{course.instructorId})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "courseId")
    Course createCourse(@Param("course") CourseCreateRequest course);

    @Delete("DELETE FROM courses WHERE course_id = #{courseId}")
    void deleteCourseById(@Param("courseId") Integer courseId);

    @Update("""
    UPDATE courses 
    SET course_name = #{course.courseName}, description = #{course.description}, instructor_id = #{course.instructorId}
    WHERE course_id = #{id}
    """)
    Course updateCourseById(@Param("id") Integer id, @Param("course") CourseUpdateRequest courseUpdateRequest);

}
