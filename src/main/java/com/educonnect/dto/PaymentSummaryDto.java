package com.educonnect.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentSummaryDto {

    private String month;
    private Double totalcollected;
    private Long studentcount;
}
