package com.example.springinaction.repetit;

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

        TestList taskList2 = new TestList();
        List<Task> taskList = new ArrayList<>();

        Task singleAnswerTask = new Task();
        singleAnswerTask.setQuestion("Свойства каких частей речи в себе соединило причастие?");
        List<String> answersList = new ArrayList<>();
        answersList.add("Глагола и имени существительного");
        answersList.add("Глагола и имени прилагательного");
        answersList.add("Наречия и глагола");
        singleAnswerTask.setType(Type.SingleAnswer);
        singleAnswerTask.setId("id1");
        singleAnswerTask.setCorrectAnswer("Глагола и имени прилагательного");
        singleAnswerTask.setAnswersList(answersList);


        Task singleAnswerTask2 = new Task();
        singleAnswerTask2.setQuestion("Выберите из приведённых вариантов ответа причастие");
        List<String> answersList2 = new ArrayList<>();
        answersList2.add("Идя");
        answersList2.add("Лежавший");
        answersList2.add("Горячо");
        singleAnswerTask2.setType(Type.SingleAnswer);
        singleAnswerTask2.setId("id2");
        singleAnswerTask2.setCorrectAnswer("Лежавший");
        singleAnswerTask2.setAnswersList(answersList2);



        Task multyAnswerTask = new Task();
        multyAnswerTask.setQuestion("Свойства каких частей речи в себе соединило причастие?");
        List<String> answersList3 = new ArrayList<>();
        answersList3.add("Глагол");
        answersList3.add("Имя прилагательное");
        answersList3.add("Наречие");
        multyAnswerTask.setType(Type.MultyAnswer);
        multyAnswerTask.setId("id3");
        multyAnswerTask.setCorrectAnswer("Глагол|Имя прилагательное");
        multyAnswerTask.setAnswersList(answersList3);


        Task multyAnswerTask2 = new Task();
        multyAnswerTask2.setQuestion("Выберите из приведённых вариантов ответа причастие");
        List<String> answersList4 = new ArrayList<>();
        answersList4.add("Идя");
        answersList4.add("Лежавший");
        answersList4.add("Бежавший");
        multyAnswerTask2.setType(Type.MultyAnswer);
        multyAnswerTask2.setId("id4");
        multyAnswerTask2.setCorrectAnswer("Бежавший|Лежавший");
        multyAnswerTask2.setAnswersList(answersList4);

        Random r = new Random();

        int num = r.nextInt(2);

        System.out.println(num);

        if (num == 0) {
            taskList.add(singleAnswerTask2);
            model.addAttribute("taskList", singleAnswerTask2);
            System.out.println(model.toString());

            taskList.add(singleAnswerTask);
            model.addAttribute("taskList", singleAnswerTask);
            System.out.println(model.toString());

            taskList.add(multyAnswerTask2);
            model.addAttribute("taskList", multyAnswerTask2);
            System.out.println(model.toString());
        } else {
            taskList.add(singleAnswerTask2);
            model.addAttribute("taskList", singleAnswerTask2);
            System.out.println(model.toString());
            taskList.add(multyAnswerTask);
            model.addAttribute("taskList", multyAnswerTask);
            System.out.println(model.toString());
        }

        System.out.println(taskList.toString());


        model.addAttribute("taskList", taskList);

        System.out.println(model.toString());
    }

    @ModelAttribute(name = "taskList")
    public TestList order(){
        return new TestList();
    }

//    @ModelAttribute(name = "task")
//    public Task task(){
//        return new Task();
//    }

    @GetMapping
    public String showDesignForm(){
        System.out.println("ShowThemeController.java");
        return "theme";
    }
}
