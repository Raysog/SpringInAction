package com.example.springinaction.repetit;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@Controller
@Slf4j
@RequestMapping("/profile")
public class ShowProfileController {

    @GetMapping
    public String showDesignForm(@RequestHeader Map<String, String> headers){
        System.out.println("_______________________________________________");
        System.out.println("GetMapping showDesignForm");
        System.out.println();
        String userAgent = headers.get("user-agent");

        System.out.println("_______________________________________________");
        return "profile";
    }

}
