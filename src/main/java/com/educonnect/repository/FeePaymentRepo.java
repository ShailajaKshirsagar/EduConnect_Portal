package com.educonnect.repository;

import com.educonnect.entity.FeePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FeePaymentRepo extends JpaRepository<FeePayment,Long>
{

    @Query("SELECT SUM(f.amount) FROM FeePayment f " +
            "WHERE FUNCTION('MONTH', f.paymentdate) =:month " +
            "AND FUNCTION('YEAR', f.paymentdate) =:year " +
            "AND f.status = 'PAID'")
    Double totalCollected(@Param("month") int month,@Param("year") int year);

    @Query("SELECT COUNT(DISTINCT f.student_id) FROM FeePayment f " +
            "WHERE FUNCTION('MONTH', f.paymentdate) = :month " +
            "AND FUNCTION('YEAR', f.paymentdate) = :year " +
            "AND f.status = 'PAID'")
    Long getPaidStudentCount(@Param("month") int month, @Param("year") int year);

}
