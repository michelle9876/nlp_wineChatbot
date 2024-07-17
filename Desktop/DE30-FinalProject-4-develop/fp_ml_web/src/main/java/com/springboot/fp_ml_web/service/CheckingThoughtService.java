package com.springboot.fp_ml_web.service;

import com.springboot.fp_ml_web.data.entity.CheckingThought;
import com.springboot.fp_ml_web.data.repository.CheckingThoughtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckingThoughtService {

    @Autowired
    private CheckingThoughtRepository repository;

    public CheckingThought saveCheckingThought(CheckingThought checkingThought) {
        return repository.save(checkingThought);
    }
}
