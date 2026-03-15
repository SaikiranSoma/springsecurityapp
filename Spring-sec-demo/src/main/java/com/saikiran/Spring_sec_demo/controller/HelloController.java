package com.saikiran.Spring_sec_demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String greet(HttpServletRequest request){
            return "Hello Saikiran Keep going " + request.getSession().getId();
    }

    @GetMapping("/about")
    public String about(HttpServletRequest request){
        return "Im learning java and my goal is to study and work in germany "+ request.getSession().getId();
    }
}
