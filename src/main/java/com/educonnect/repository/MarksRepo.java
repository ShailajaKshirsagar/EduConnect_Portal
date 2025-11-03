package com.educonnect.repository;

import com.educonnect.entity.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.error.Mark;

@Repository
public interface MarksRepo extends JpaRepository<Marks,Long> {
}
