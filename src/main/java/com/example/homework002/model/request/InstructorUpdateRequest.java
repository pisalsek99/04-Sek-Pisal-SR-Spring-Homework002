package com.example.homework002.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorUpdateRequest {
    private String instructorName;
    @Schema(defaultValue = "example@gmail.com")
    private String email;
}
