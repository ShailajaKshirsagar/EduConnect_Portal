package com.educonnect.repository;

import com.educonnect.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

    //search by keyword
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "OR LOWER(p.description) LIKE LOWER(CONCAT('%',:keyword,'%'))")
    List<Product> searchProduct(@Param("keyword") String keyword);
}
