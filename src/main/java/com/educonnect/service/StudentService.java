package com.educonnect.service;

import com.educonnect.dto.StudentReponseDto;
import com.educonnect.dto.StudentRequestDto;

public interface StudentService {
    String createStudent(StudentRequestDto dto);

    StudentReponseDto getStudentById(long id);
}
