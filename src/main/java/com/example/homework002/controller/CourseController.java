package com.example.homework002.controller;

import com.example.homework002.model.Course;
import com.example.homework002.model.response.ApiResponse;
import com.example.homework002.model.request.CourseCreateRequest;
import com.example.homework002.model.request.CourseUpdateRequest;
import com.example.homework002.model.response.ErrorResponse;
import com.example.homework002.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> findAllCourses(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        ApiResponse<List<Course>> response = ApiResponse.<List<Course>>builder()
                .message("Find all courses successfully")
                .payload(courseService.findAllCourses(page, size))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCourseById(@PathVariable Integer id) {
        Course course = courseService.findCourseById(id);

        if (course != null) {
            return ResponseEntity.ok(ApiResponse.<Course>builder()
                    .message("Find course by ID successfully")
                    .payload(course)
                    .status(HttpStatus.OK)
                    .timestamp(LocalDateTime.now())
                    .build());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
                "about:blank",
                "Not Found",
                HttpStatus.NOT_FOUND.value(),
                "Course with ID " + id + " not found.",
                "/api/v1/courses/" + id,
                LocalDateTime.now()
        ));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> createCourse(@RequestBody CourseCreateRequest courseCreateRequest) {
        Course newCourse = courseService.createCourse(courseCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<Course>builder()
                .message("Create new course successfully")
                .payload(newCourse)
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable Integer id) {
        if (courseService.findCourseById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
                    "about:blank",
                    "Not Found",
                    HttpStatus.NOT_FOUND.value(),
                    "Course with ID " + id + " not found.",
                    "/api/v1/courses/" + id,
                    LocalDateTime.now()
            ));
        }

        courseService.deleteCourseById(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Course deleted successfully")
                .status(HttpStatus.NO_CONTENT)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourseById(@PathVariable Integer id, @RequestBody CourseUpdateRequest courseUpdateRequest) {
        if (courseService.findCourseById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
                    "about:blank",
                    "Not Found",
                    HttpStatus.NOT_FOUND.value(),
                    "Course with ID " + id + " not found.",
                    "/api/v1/courses/" + id,
                    LocalDateTime.now()
            ));
        }

        Course updatedCourse = courseService.updateCourseById(id, courseUpdateRequest);
        return ResponseEntity.ok(ApiResponse.<Course>builder()
                .message("Course updated successfully")
                .payload(updatedCourse)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build());
    }
}
