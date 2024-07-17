package com.springboot.fp_ml_web.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @Operation(summary = "테스트용 api")
    @GetMapping("/api/data")
    public String test() {
        return "Hello, world!";
    }
    
//    수정테스트

    // 작업했음
}

