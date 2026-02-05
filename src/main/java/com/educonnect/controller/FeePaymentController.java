package com.educonnect.controller;

import com.educonnect.dto.PaymentSummaryDto;
import com.educonnect.entity.FeePayment;
import com.educonnect.service.FeePaymentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class FeePaymentController
{
    @Autowired
    private FeePaymentService feePaymentService;

    @PostMapping("/addPayment")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    public ResponseEntity<FeePayment> addPayment(@RequestBody FeePayment payment){
        FeePayment fees = feePaymentService.addPayment(payment);
        return new ResponseEntity<>(fees, HttpStatus.CREATED);
    }

    //get payment summary by month
    @GetMapping("/getPaymentSummary")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    public ResponseEntity<PaymentSummaryDto> getPaymentSummary(@RequestParam String month){
        PaymentSummaryDto response = feePaymentService.getPaymentSummary(month);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
