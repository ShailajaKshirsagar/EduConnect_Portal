package com.educonnect.repository;

import com.educonnect.entity.FeePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeePaymentRepo extends JpaRepository<FeePayment,Long> {
}
