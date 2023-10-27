package com.example.springinaction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowThemeController {

    @GetMapping("/")
    public String home(){
        System.out.println("ShowThemeController.java");
        return "example";
    }
}
