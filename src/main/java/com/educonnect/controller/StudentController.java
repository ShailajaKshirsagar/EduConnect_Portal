package com.educonnect.controller;

import com.educonnect.dto.StudentReponseDto;
import com.educonnect.dto.StudentRequestDto;
import com.educonnect.entity.Student;
import com.educonnect.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
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

    //filter student by department with option to paginate
    @GetMapping("/getStudents")
    @PreAuthorize("hasAnyRole('ADMIN','FACULTY','HR')")
    public ResponseEntity<Page<StudentReponseDto>> getStudents(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "lname,asc") String sort,
                                                     @RequestParam(required = false) String department){

        String[] sortParams = sort.split(",");
        String sortBy = sortParams[0];
        Sort.Direction direction = Sort.Direction.ASC;

        if (sortParams.length > 1) {
            direction = Sort.Direction.fromString(sortParams[1]);
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<StudentReponseDto> students = studentService.getStudentByDepartment(department, pageable);

        return new ResponseEntity<>(students,HttpStatus.OK);
    }
}
