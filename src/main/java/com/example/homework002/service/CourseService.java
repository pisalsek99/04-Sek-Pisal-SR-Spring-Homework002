package com.example.homework002.service;

import com.example.homework002.model.Course;
import com.example.homework002.model.request.CourseCreateRequest;
import com.example.homework002.model.request.CourseUpdateRequest;

import java.util.List;

public interface CourseService {

    List<Course> findAllCourses(Integer page, Integer size);
    Course findCourseById(Integer id);
    Course createCourse(CourseCreateRequest courseCreateRequest);
    void deleteCourseById(Integer id);
    Course updateCourseById(Integer id, CourseUpdateRequest courseUpdateRequest);
}
