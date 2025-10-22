package com.educonnect.dto;

import lombok.*;

@Setter
@ Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentReponseDto
{
    private String fname;
    private String lname;
    private String department;
    private String year;
    private int age;
}
