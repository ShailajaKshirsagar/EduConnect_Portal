package com.educonnect.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportCardResponseDto
{
    private Long studentId;
    private String studentName;
    private String className;
    private ReportCardDto reportCard;
}
