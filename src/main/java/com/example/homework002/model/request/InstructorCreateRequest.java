package com.example.homework002.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorCreateRequest {
    private String instructorName;
    @Schema(defaultValue = "example@gmail.com")
    private String email;
}
