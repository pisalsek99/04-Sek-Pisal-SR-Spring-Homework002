package com.example.homework002.service;

import com.example.homework002.model.Instructor;
import com.example.homework002.model.request.InstructorCreateRequest;
import com.example.homework002.model.request.InstructorUpdateRequest;


import java.util.List;

public interface InstructorService {
    List<Instructor> findAllInstructors(Integer page, Integer size);
    Instructor createInstructor(InstructorCreateRequest instructorCreateRequest);
    Instructor findInstructorById(Integer id);
    void deleteInstructorById(Integer id);
    Instructor updateInstructorById(Integer id, InstructorUpdateRequest instructorUpdateRequest);
}
