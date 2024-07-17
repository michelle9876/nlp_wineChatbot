//package com.springboot.fp_ml_web.controller;
//
//import com.springboot.fp_ml_web.data.entity.User;
//import com.springboot.fp_ml_web.data.repository.UserRepository;
//import com.springboot.fp_ml_web.service.UserService;
//import io.swagger.v3.oas.annotations.Operation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@Controller
////@RestController
//public class HomeController {
//
////    수정테스트
////    @Operation(summary = "테스트용 api")
////    @GetMapping("/api/data")
////    public String test() {
////        return "Hello, world!";
////    }
////
////
//    private final UserRepository userRepository;
//    private final UserService userService;
//
//    @GetMapping("/")
//    @ResponseBody
//    public String homePage(Model model){
//        List<User> userList = this.userService.getUser();
//        model.addAttribute("userList", userList);
//
//        return "This is Home Page";
//    }
//}