package com.educonnect.serviceImpl;

import com.educonnect.dto.StudentReponseDto;
import com.educonnect.dto.StudentRequestDto;
import com.educonnect.entity.Student;
import com.educonnect.entity.User;
import com.educonnect.repository.StudentRepo;
import com.educonnect.repository.UserRepo;
import com.educonnect.service.StudentService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String createStudent(StudentRequestDto dto) {

        User user = User.builder()
                .username(dto.getUsername())
                .password(encoder.encode(dto.getPassword()))
                .active(true)
                .role(User.Role.STUDENT).build();

        Student student = Student.builder()
                .fname(dto.getFname())
                .lname(dto.getLname())
                .year(dto.getYear())
                .age(dto.getAge())
                .department(dto.getDepartment())
                .user(user)
                .build();

        studentRepository.save(student);

        return "Student created with username : "+ user.getUsername();
    }

    @Override
    public StudentReponseDto getStudentById(long id) {
        Student Student= studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id"));

        StudentReponseDto dto = StudentReponseDto.builder()
                .fname(Student.getFname())
                .lname(Student.getLname())
                .age(Student.getAge())
                .department(Student.getDepartment())
                .year(Student.getYear())
                .build();

        return dto;
    }

    @Override
    public Page<StudentReponseDto> getStudentByDepartment(String department, Pageable pageable) {

        Page<Student> students;

        if(department==null || department.isEmpty()){
            students = studentRepository.findAll(pageable);
        }
        else {
            students = studentRepository.filterByDepartmentContainingIgnoreCase(department, pageable);
        }
        return students.map(student -> StudentReponseDto.builder()
                .fname(student.getFname())
                .lname(student.getLname())
                .department(student.getDepartment())
                .age(student.getAge())
                .year(student.getYear())
                .build());
    }
}
