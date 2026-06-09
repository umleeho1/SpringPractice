package com.um.premium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignupController {

    @GetMapping("/signup")
    public String signupForm() {
        return "main/signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String username,
                         @RequestParam String password,
                         @RequestParam String email) {
        System.out.println("아이디: " + username);
        System.out.println("비밀번호: " + password);
        System.out.println("이메일: " + email);
        return "main/index";
    }
}
