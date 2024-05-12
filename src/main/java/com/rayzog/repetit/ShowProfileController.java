package com.rayzog.repetit;

import com.rayzog.repetit.dao.TaskRepository;
import com.rayzog.repetit.dao.UserRepository;
import com.rayzog.repetit.testTask.TestList;
import com.rayzog.repetit.testTask.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.*;

@Controller
@Slf4j
@RequestMapping("/profile")
public class ShowProfileController {

    private UserRepository userRepository;

    @Autowired
    public ShowProfileController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @ModelAttribute(name = "user")
    public User user(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        return user;
    }

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
