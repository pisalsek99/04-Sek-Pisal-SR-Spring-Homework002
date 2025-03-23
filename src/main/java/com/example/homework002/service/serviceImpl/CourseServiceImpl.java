package com.example.homework002.service.serviceImpl;

import com.example.homework002.model.Course;
import com.example.homework002.model.request.CourseCreateRequest;
import com.example.homework002.model.request.CourseUpdateRequest;
import com.example.homework002.repository.CourseRepository;
import com.example.homework002.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAllCourses(Integer page, Integer size) {
        return courseRepository.findAllCourses(page, size);
    }

    @Override
    public Course findCourseById(Integer id) {
        return courseRepository.findCourseById(id);
    }

    @Override
    public Course createCourse(CourseCreateRequest courseCreateRequest) {
        return courseRepository.createCourse(courseCreateRequest);
    }

    @Override
    public void deleteCourseById(Integer id) {
        courseRepository.deleteCourseById(id);
    }

    @Override
    public Course updateCourseById(Integer id, CourseUpdateRequest courseUpdateRequest) {
        return courseRepository.updateCourseById(id, courseUpdateRequest);
    }
}
