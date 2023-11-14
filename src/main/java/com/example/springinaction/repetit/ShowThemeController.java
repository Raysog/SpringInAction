package com.example.springinaction;

import com.example.springinaction.repetit.testTask.TestList;
import com.example.springinaction.repetit.testTask.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.springinaction.repetit.testTask.Task.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
@Slf4j
@SessionAttributes("taskList")
@RequestMapping("/theme")
public class ShowThemeController {

    @ModelAttribute
    public void addQuestionsToModel(Model model) {

        TestList taskList = new TestList();

        Task singleAnswerTask = new Task();
        singleAnswerTask.setQuestion("Свойства каких частей речи в себе соединило причастие?");
        List<String> answersList = new ArrayList<>();
        answersList.add("Глагола и имени существительного");
        answersList.add("Глагола и имени прилагательного");
        answersList.add("Наречия и глагола");
        singleAnswerTask.setType(Type.SingleAnswer);
        singleAnswerTask.setAnswersList(answersList);


        Task singleAnswerTask2 = new Task();
        singleAnswerTask2.setQuestion("Выберите из приведённых вариантов ответа причастие");
        List<String> answersList2 = new ArrayList<>();
        answersList2.add("Идя");
        answersList2.add("Лежавший");
        answersList2.add("Горячо");
        singleAnswerTask2.setType(Type.SingleAnswer);
        singleAnswerTask2.setAnswersList(answersList2);



        Task multyAnswerTask = new Task();
        multyAnswerTask.setQuestion("Свойства каких частей речи в себе соединило причастие?");
        List<String> answersList3 = new ArrayList<>();
        answersList3.add("Глагол");
        answersList3.add("Имя прилагательное");
        answersList3.add("Наречие");
        multyAnswerTask.setType(Type.MultyAnswer);
        multyAnswerTask.setAnswersList(answersList3);


        Task multyAnswerTask2 = new Task();
        multyAnswerTask2.setQuestion("Выберите из приведённых вариантов ответа причастие");
        List<String> answersList4 = new ArrayList<>();
        answersList4.add("Идя");
        answersList4.add("Лежавший");
        answersList4.add("Бежавший");
        multyAnswerTask2.setType(Type.MultyAnswer);
        multyAnswerTask2.setAnswersList(answersList4);

        Random r = new Random();

        int num = r.nextInt(2);

        System.out.println(num);

        if (num == 0) {
            taskList.addTask(singleAnswerTask);
            taskList.addTask(multyAnswerTask);
        } else {
            taskList.addTask(singleAnswerTask2);
            taskList.addTask(multyAnswerTask2);
        }

        model.addAttribute("taskList", taskList);

        System.out.println(model.toString());
    }

    @ModelAttribute(name = "taskList")
    public TestList order(){
        return new TestList();
    }

    @ModelAttribute(name = "task")
    public Task task(){
        return new Task();
    }

    @GetMapping
    public String showDesignForm(){
        System.out.println("ShowThemeController.java");
        return "theme";
    }
}
