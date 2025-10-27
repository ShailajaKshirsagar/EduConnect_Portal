package com.educonnect.serviceImpl;

import com.educonnect.entity.FeePayment;
import com.educonnect.repository.FeePaymentRepo;
import com.educonnect.repository.StudentRepo;
import com.educonnect.service.FeePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
}
