package com.example.homework002.controller;

import com.example.homework002.model.Instructor;
import com.example.homework002.model.request.InstructorCreateRequest;
import com.example.homework002.model.request.InstructorUpdateRequest;
import com.example.homework002.model.response.ApiResponse;
import com.example.homework002.model.response.ErrorResponse;
import com.example.homework002.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> findAllInstructors(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ){
        ApiResponse<List<Instructor>> response = ApiResponse.<List<Instructor>>builder()
                .message("Find all instructors successfully")
                .payload(instructorService.findAllInstructors(page, size))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> createInstructor(@RequestBody InstructorCreateRequest instructorCreateRequest) {
        Instructor createdInstructor = instructorService.createInstructor(instructorCreateRequest);

        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .message("Instructor created successfully")
                .payload(createdInstructor)
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findInstructorById(@PathVariable Integer id) {
        Instructor instructor = instructorService.findInstructorById(id);

        if (instructor != null) {
            ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                    .message("Find instructor by ID successfully")
                    .payload(instructor)
                    .status(HttpStatus.OK)
                    .timestamp(LocalDateTime.now())
                    .build();
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
                "about:blank",
                "Not Found",
                HttpStatus.NOT_FOUND.value(),
                "Instructor with ID " + id + " not found.",
                "/api/v1/instructors/" + id,
                LocalDateTime.now()
        ));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInstructorById(@PathVariable Integer id) {
        Instructor instructor = instructorService.findInstructorById(id);

        if (instructor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
                    "about:blank",
                    "Not Found",
                    HttpStatus.NOT_FOUND.value(),
                    "Instructor with ID " + id + " not found.",
                    "/api/v1/instructors/" + id,
                    LocalDateTime.now()
            ));
        }

        instructorService.deleteInstructorById(id);
        ApiResponse<Object> response = ApiResponse.<Object>builder()
                .message("Instructor deleted successfully")
                .status(HttpStatus.NO_CONTENT)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInstructorById(@PathVariable Integer id, @RequestBody InstructorUpdateRequest instructorUpdateRequest) {
        Instructor existingInstructor = instructorService.findInstructorById(id);

        if (existingInstructor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
                    "about:blank",
                    "Not Found",
                    HttpStatus.NOT_FOUND.value(),
                    "Instructor with ID " + id + " not found.",
                    "/api/v1/instructors/" + id,
                    LocalDateTime.now()
            ));
        }

        Instructor updatedInstructor = instructorService.updateInstructorById(id, instructorUpdateRequest);

        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .message("Instructor updated successfully")
                .payload(updatedInstructor)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
