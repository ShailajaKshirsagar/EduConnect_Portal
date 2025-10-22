package com.educonnect.controller;

import com.educonnect.dto.StudentReponseDto;
import com.educonnect.dto.StudentRequestDto;
import com.educonnect.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/student")
@Tag(name = "Student Management",description = "API's for managing students")
public class StudentController
{
    @Autowired
    private StudentService studentService;

    @PostMapping("/addStudent")
    @Operation(summary = "Add a new student", description = "Creates a student and linked user")
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @SecurityRequirement(name = "basicAuth")
    public ResponseEntity<String> addStudent(@RequestBody StudentRequestDto dto){
      String msg  = studentService.createStudent(dto);
      return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/getStudentById/{id}")
    @Operation(summary = "Get student detail by id", description = "Return student Detail")
    @PreAuthorize("hasAnyRole('ADMIN','FACULTY','HR')")
    @SecurityRequirement(name = "basicAuth")
    public ResponseEntity<StudentReponseDto> getStudentById(@PathVariable("id") long id){
        StudentReponseDto studentById = studentService.getStudentById(id);
        return new ResponseEntity<>(studentById,HttpStatus.OK);
    }
}
