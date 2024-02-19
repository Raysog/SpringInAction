package com.example.springinaction.repetit;

import com.example.springinaction.repetit.testTask.LoginForm;
import com.example.springinaction.repetit.testTask.Task;
import com.example.springinaction.repetit.testTask.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/")
@SessionAttributes("loginForm")
public class LoginController {


    private LoginForm loginForm;



    @GetMapping
    public String showDesignForm(Model model, @RequestHeader Map<String, String> headers){
        System.out.println("_______________________________________________");
        System.out.println("GetMapping login");
        System.out.println();
        String userAgent = headers.get("user-agent");

        model.addAttribute("loginForm", this.loginForm);

        System.out.println(model.toString());

        System.out.println("_______________________________________________");
        return "login";
    }

    @ModelAttribute(name = "loginForm")
    public LoginForm loginForm(){
        System.out.println("Create new LoginForm");
        if (this.loginForm == null){
            System.out.println("return new LoginForm");
            LoginForm loginForm = new LoginForm();
            loginForm.setLogin("aaa");
            loginForm.setPass("aaa");
            return loginForm;
        } else {
            System.out.println("return current LoginForm");
            return this.loginForm;
        }
    }

    @PostMapping("checkLogin")
    public String checkLogin(
            @ModelAttribute("loginForm") LoginForm loginForm
            , @RequestHeader Map<String, String> headers){
        System.out.println("CheckLogin");
        String userAgent = headers.get("user-agent");
        System.out.println(userAgent);

        System.out.println(loginForm);


        if (loginForm.getLogin() == "login" && loginForm.getPass() =="pass") {
            return "redirect:/";
        } else {
            return "redirect:/profile";
        }
    }

}
