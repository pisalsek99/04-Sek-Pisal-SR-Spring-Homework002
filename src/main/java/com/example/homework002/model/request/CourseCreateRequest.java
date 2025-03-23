package com.example.homework002.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseCreateRequest {
    private String courseName;
    private String description;
    private Integer instructorId;
}
