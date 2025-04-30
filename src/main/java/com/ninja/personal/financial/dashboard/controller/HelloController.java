package com.ninja.personal.financial.dashboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
    @GetMapping("hello")
    public String helloWorld(){
        return "Hello World! Your backend is working";
    }
}
