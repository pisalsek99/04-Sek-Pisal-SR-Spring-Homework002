package com.example.homework002.controller;

import com.example.homework002.model.Student;
import com.example.homework002.model.request.StudentCreateRequest;
import com.example.homework002.model.request.StudentUpdateRequest;
import com.example.homework002.model.response.ApiResponse;
import com.example.homework002.model.response.ErrorResponse;
import com.example.homework002.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> findAllStudents(@RequestParam(defaultValue = "1") Integer page,
                                                                      @RequestParam(defaultValue = "10") Integer size){
        ApiResponse<List<Student>> response = ApiResponse.<List<Student>>builder()
                .message("Find all students is successfully")
                .payload(studentService.findAllStudents(page, size))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findStudentById(@PathVariable Integer id){
        Student student = studentService.findStudentById(id);

        if(student != null){
            ApiResponse<Student> response = ApiResponse.<Student>builder()
                    .message("Find student by id is successfully")
                    .payload(student)
                    .status(HttpStatus.OK)
                    .timestamp(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
                "about:blank",
                "Not Found",
                HttpStatus.NOT_FOUND.value(),
                "Students with ID " + id + " not found.",
                "/api/v1/students/" + id,
                LocalDateTime.now()
        ));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> createStudent(@RequestBody StudentCreateRequest studentCreateRequest){
        Student newStudent = studentService.createStudent(studentCreateRequest);
        System.out.println(newStudent);
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("Student is successfully created")
                .payload(newStudent)
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Integer id){
        Student existingStudent = studentService.findStudentById(id);

        if(existingStudent == null){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
                    "about:blank",
                    "Not Found",
                    HttpStatus.NOT_FOUND.value(),
                    "Students with ID " + id + " not found.",
                    "/api/v1/students/" + id,
                    LocalDateTime.now()
            ));
        }
        studentService.deleteStudentById(id);


        return ResponseEntity.ok(ApiResponse.<Student>builder()
                .message("Delete student is successfully deleted")
                .payload(existingStudent)
                .status(HttpStatus.NO_CONTENT)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudentById(@PathVariable Integer id,@RequestBody StudentUpdateRequest studentUpdateRequest){
        Student exitingStudent = studentService.findStudentById(id);
        if (exitingStudent == null){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
                    "about:blank",
                    "Not Found",
                    HttpStatus.NOT_FOUND.value(),
                    "Students with ID " + id + " not found.",
                    "/api/v1/students/" + id,
                    LocalDateTime.now()
            ));
        }
        Student updatedStudent = studentService.updateStudentById(id, studentUpdateRequest);

        return ResponseEntity.ok(ApiResponse.<Student>builder()
                .message("Update student is successfully")
                .payload(updatedStudent)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build());
    }
}
