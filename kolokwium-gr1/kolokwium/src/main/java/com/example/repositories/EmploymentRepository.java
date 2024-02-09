package com.example.repositories;

import com.example.model.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface EmploymentRepository extends JpaRepository<Employment, Integer> {
    Employment findByTitle(@Param("title") String title);
}
