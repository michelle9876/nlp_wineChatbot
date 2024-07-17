package com.springboot.fp_ml_web.service;

import com.springboot.fp_ml_web.data.entity.User;
import com.springboot.fp_ml_web.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }
}
