package com.educonnect.repository;

import com.educonnect.dto.StudentReponseDto;
import com.educonnect.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {

    @Query("SELECT s FROM Student s WHERE LOWER(s.department) LIKE LOWER(CONCAT('%', :department, '%'))")
    Page<Student> filterByDepartmentContainingIgnoreCase(@Param("department") String department, Pageable pageable);
}
