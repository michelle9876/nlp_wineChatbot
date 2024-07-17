package com.springboot.fp_ml_web.controller;

import com.springboot.fp_ml_web.data.entity.User;
import com.springboot.fp_ml_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/nickname")
    public User saveUserNickName(@RequestBody User user){
        System.out.println(user);
        return userService.saveUser(user);
    }

}
