package com.educonnect.service;

import com.educonnect.dto.StudentReponseDto;
import com.educonnect.dto.StudentRequestDto;
import com.educonnect.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    String createStudent(StudentRequestDto dto);

    StudentReponseDto getStudentById(long id);

    Page<StudentReponseDto> getStudentByDepartment(String department, Pageable pageable);
}
