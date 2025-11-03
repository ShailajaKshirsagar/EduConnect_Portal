package com.educonnect.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportCardDto {

    private List<SubjectScoreDto> subjects;
    private double total;
    private double percentage;
    private String status;
}
