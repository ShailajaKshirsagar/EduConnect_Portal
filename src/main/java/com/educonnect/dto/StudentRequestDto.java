package com.educonnect.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDto
{
    private String fname;
    private String lname;
    private String department;
    private String year;
    private int age;

    private String username;
    private String password;
}
