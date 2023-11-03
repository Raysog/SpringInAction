package com.example.springinaction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String home(){
        System.out.println("MainController.java");
        return "main";
    }
}
