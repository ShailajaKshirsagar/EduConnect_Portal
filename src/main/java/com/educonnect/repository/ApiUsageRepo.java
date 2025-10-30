package com.educonnect.repository;

import com.educonnect.entity.ApiUsage;
import com.educonnect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ApiUsageRepo extends JpaRepository<ApiUsage,Long> {

    @Query("SELECT a FROM ApiUsage a WHERE a.user =:user AND a.usagedate =:usagedate")
    Optional<ApiUsage> findByUserAndUsageDate(@Param("user") User user, @Param("usagedate") LocalDate usagedate);
}
