package com.educonnect.serviceImpl;

import com.educonnect.dto.PaymentSummaryDto;
import com.educonnect.entity.FeePayment;
import com.educonnect.repository.FeePaymentRepo;
import com.educonnect.repository.StudentRepo;
import com.educonnect.service.FeePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

@Service
public class FeePaymentServiceImpl implements FeePaymentService {

    @Autowired
    private FeePaymentRepo feePaymentRepository;

    @Autowired
    private StudentRepo studentRepository;

    @Override
    public FeePayment addPayment(FeePayment payment) {

        //for validation
        System.out.println(">>> Validating student ID: " + payment.getStudent_id());

        if (!studentRepository.existsById(payment.getStudent_id())) {
            throw new RuntimeException("Invalid student ID: " + payment.getStudent_id());
        }

        if(payment.getPaymentdate()==null){
            payment.setPaymentdate(LocalDate.now());
        }
        if(payment.getStatus()==null){
            payment.setStatus("PAID");
        }
        FeePayment payment1 = feePaymentRepository.save(payment);
        return payment1;
    }

    @Override
    public PaymentSummaryDto getPaymentSummary(String month) {

        YearMonth yearMonth = YearMonth.parse(month);
        int year  = yearMonth.getYear();
        int monthValue =yearMonth.getMonthValue();

        Double totalCollected = feePaymentRepository.totalCollected(monthValue,year);
        Long paidStudentCount = feePaymentRepository.getPaidStudentCount(monthValue,year);

        if(totalCollected==null){
            totalCollected=0.0;
        }
        if(paidStudentCount==null){
            paidStudentCount=0L;
        }
        return PaymentSummaryDto.builder()
                .month(month)
                .totalcollected(totalCollected)
                .studentcount(paidStudentCount)
                .build();
    }
}
