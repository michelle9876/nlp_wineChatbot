package com.springboot.fp_ml_web.data.repository;

import com.springboot.fp_ml_web.data.entity.CheckingThought;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CheckingThoughtRepository extends JpaRepository<CheckingThought, Integer> {
}