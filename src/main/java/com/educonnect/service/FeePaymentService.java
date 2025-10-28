package com.educonnect.service;

import com.educonnect.dto.PaymentSummaryDto;
import com.educonnect.entity.FeePayment;

public interface FeePaymentService {
    FeePayment addPayment(FeePayment payment);

    PaymentSummaryDto getPaymentSummary(String month);
}
