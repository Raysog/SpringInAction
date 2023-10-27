package com.example.springinaction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetQuestionController {

    @GetMapping("/")
    public String home(){
        System.out.println("HomeController.java");
        return "home";
    }
}
