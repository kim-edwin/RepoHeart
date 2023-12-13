package com.example.heart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/ver1")
@RestController
public class ApiController {
    
    //localhost:8080/api/ver1/hello
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    //localhost:8080/api/ver1/hello2
    @GetMapping("/hello2")
    public String hello2() {
        return "Hello Minan, 이번엔 CREATE Branch 적용 후 새롭게 적어봅니다!";
    }

    @GetMapping("/hello4")
    public String hello4() {
        return "gyeong min이가 아님";
    }
}