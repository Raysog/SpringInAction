package com.example.springinaction.repetit;

import com.example.springinaction.Taco;
import com.example.springinaction.TacoOrder;
import com.example.springinaction.repetit.testTask.TestList;
import com.example.springinaction.repetit.testTask.Task;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.example.springinaction.repetit.testTask.Task.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
@Slf4j
@SessionAttributes("task")
@RequestMapping("/theme")
public class ShowThemeController {

    @ModelAttribute
    public void addQuestionsToModel(Model model) {

        TestList taskList = TestList.getInstance();
        taskList.getTaskList();

        Task firstTask = taskList.getTaskList().get(0);

//        System.out.println(taskList.toString());



        model.addAttribute("task", firstTask);
        model.addAttribute("taskNumberList", taskList.getTaskNumberList());

        System.out.println(model.toString());
    }

    @ModelAttribute(name = "task")
    public Task order(){
        return new Task();
    }


    @GetMapping
    public String showDesignForm(){
        System.out.println("ShowThemeController.java");
        return "theme";
    }

    @PostMapping
    public String checkAnswer(
            @ModelAttribute Task task){

        if (task.getCorrectAnswer().equals(task.getStudentAnswer())) {
            task.setResult("yes");
        } else {
            task.setResult("no");
        }

        System.out.println( task.toString());
//        log.info("Processing taco: {}", task);

        return "redirect:/theme";
    }
}
