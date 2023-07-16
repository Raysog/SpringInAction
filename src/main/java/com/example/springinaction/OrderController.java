package com.example.springinaction;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    @PostMapping
    public String processOrder(
            TacoOrder tacoOrder,
            SessionStatus sessionStatus){

//        if (errors.hasErrors()) {
//            System.out.println("asdffasdfasdfasdf");
//            return "orderForm";
//        }

        log.info("Order submitted: {}", tacoOrder);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
