package com.example.springinaction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GetQuestionController {

    @GetMapping("/getData")
    @ResponseBody
    public String getData(@RequestParam("param1") String param1) {
        // Обработка запроса и формирование ответа
        String result = "Вопрос  " + param1;
        return result;
    }
}
