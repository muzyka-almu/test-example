package com.example.testing.controller;

import com.example.testing.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    TestService testService;

    @RequestMapping("/")
    public String hello() {
        return "Hello world";
    }
}
