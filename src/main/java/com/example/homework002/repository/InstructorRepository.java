package com.example.homework002.repository;

import org.apache.ibatis.annotations.*;
import  com.example.homework002.model.Instructor;
import  com.example.homework002.model.request.InstructorCreateRequest;
import  com.example.homework002.model.request.InstructorUpdateRequest;

import java.util.List;

@Mapper
public interface InstructorRepository {
    @Select("""
        SELECT * FROM instructors
        offset #{size} * (#{page} - 1)
        limit #{size}
    """)
    @Results(id = "instructorMapper", value = {
            @Result(property = "instructorId", column = "instructor_id"),
            @Result(property = "instructorName", column = "instructor_name")
    })
    List<Instructor> findAllInstructors(Integer page, Integer size);

    @Select(("""
        INSERT INTO instructors(instructor_name, email) 
        VALUES (#{instructor.instructorName},#{instructor.email}) 
        RETURNING *;
"""))
    @ResultMap("instructorMapper")
    Instructor createInstructor(@Param("instructor") InstructorCreateRequest instructorCreateRequest);

    @Select("SELECT * FROM instructors WHERE instructor_id = #{instructorId}")
    @ResultMap("instructorMapper")
    Instructor findInstructorById(Integer id);

    @Select("DELETE FROM instructors WHERE instructor_id = #{instructorId}")
    void deleteInstructorById(Integer id);

    @Select("UPDATE instructors SET instructor_name = #{instructor.instructorName}, email = #{instructor.email} WHERE instructor_id = #{id} RETURNING *")
    @ResultMap("instructorMapper")
    Instructor updateInstructorById(@Param("id") Integer id,@Param("instructor") InstructorUpdateRequest instructorUpdateRequest);


}
