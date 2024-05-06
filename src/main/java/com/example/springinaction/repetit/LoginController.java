//package com.example.springinaction.repetit;
//
//
//import com.example.springinaction.repetit.dao.UserReader;
//import com.example.springinaction.repetit.testTask.LoginForm;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@Controller
//@Slf4j
//@RequestMapping("/")
//@SessionAttributes("loginForm")
//public class LoginController {
//
//    private final UserReader userReader;
//    private LoginForm loginForm;
//
//
//    @Autowired
//    public LoginController(UserReader userReader){
//        this.userReader = userReader;
//    }
//    @GetMapping
//    public String showDesignForm(Model model, @RequestHeader Map<String, String> headers,
//                                 @CookieValue(value = "userName",
//                                         defaultValue = "#defaultCookieValue") String cookieValue){
//        System.out.println("_______________________________________________");
//        System.out.println("GetMapping login");
//        System.out.println(cookieValue);
//        String userAgent = headers.get("user-agent");
//
//        if (cookieValue.equals("#defaultCookieValue")) {
//            model.addAttribute("loginForm", this.loginForm);
//
//            System.out.println(model.toString());
//
//            System.out.println("_______________________________________________");
//            return "login";
//        } else {
//            return "redirect:/profile";
//        }
//    }
//
//    @ModelAttribute(name = "loginForm")
//    public LoginForm loginForm(){
//        System.out.println("Create new LoginForm");
//        if (this.loginForm == null){
//            System.out.println("return new LoginForm");
//            this.loginForm = new LoginForm();
//            return this.loginForm;
//        } else {
//            System.out.println("return current LoginForm");
//            return this.loginForm;
//        }
//    }
//
//    @PostMapping("checkLogin")
//    public String checkLogin(
//            @ModelAttribute("loginForm") LoginForm loginForm
//            , @RequestHeader Map<String
//            , String> headers
//            , HttpServletResponse response){
//        System.out.println("CheckLogin");
//        String userAgent = headers.get("user-agent");
//        System.out.println(userAgent);
//
//        System.out.println(loginForm);
//        System.out.println(loginForm.getPass().hashCode());
//
//        boolean isUserExist = userReader.findUserByUserLogin(loginForm);
//        System.out.println(isUserExist);
//
//        if (isUserExist) {
//            Cookie cookie = new Cookie("userName", loginForm.getLogin());
//            cookie.setMaxAge(120);
//            response.addCookie(cookie);
//            return "redirect:/profile";
//        } else {
//            return "redirect:/";
//        }
//    }
//
//}
