package com.educonnect.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectScoreDto {

    private String subject;
    private double marks;
    private String grade;
}
