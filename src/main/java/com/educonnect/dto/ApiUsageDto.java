package com.educonnect.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ApiUsageDto {

    private long userid;
    private String username;
    private long apirequestcount;
    private LocalDate usagedate;
}
