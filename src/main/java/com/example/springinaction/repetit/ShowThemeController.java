package com.example.springinaction.repetit;

import com.example.springinaction.Taco;
import com.example.springinaction.TacoOrder;
import com.example.springinaction.repetit.testTask.TestList;
import com.example.springinaction.repetit.testTask.Task;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ConcurrentModel;
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
@SessionAttributes({"task", "taskList"})
@RequestMapping("/theme")
public class ShowThemeController {

    private TestList taskList;
    private Task task;



    @ModelAttribute
    public void addQuestionsToModel(Model model) {

        System.out.println("addQuestionsToModelWithTask");

        taskList = TestList.getInstance();

        if (task == null){
            System.out.println("first try");
            Task initor = taskList.getTaskList().get(0);
            task = new Task();
            task.setQuestion(initor.getQuestion());
            task.setAnswersList(initor.getAnswersList());
            task.setCorrectAnswer(initor.getCorrectAnswer());
            task.setId(initor.getId());
            task.setType(initor.getType());
            task.setResult(initor.getResult());
            model.addAttribute("task", task);
            model.addAttribute("taskList", taskList.getTaskList());
        }
        else {
            System.out.println("not first try");
        }
        System.out.println("task after check: " + task.toString());
        System.out.println(model.toString());
        System.out.println("___________________________________________________");

    }

    @ModelAttribute(name = "task")
    public Task task(){
        System.out.println("Create new Task");
        if (this.task == null){
            System.out.println("return new task");
            return new Task();
        } else {
            System.out.println("return current task");
            return this.task;
        }
    }


    @GetMapping
    public String showDesignForm(){
        System.out.println("GetMapping showDesignForm");
        System.out.println(task.toString());
        System.out.println("_______________________________________________");
        return "theme";
    }

    @PostMapping("/checkAnswer")
    public String checkAnswer(
            @ModelAttribute Task task){
        System.out.println("CheckAnswer");
        if (task.getCorrectAnswer().equals(task.getStudentAnswer())) {
            task.setResult("yes");
        } else {
            task.setResult("no");
        }
//        log.info("Processing taco: {}", task);

        return "redirect:/theme";
    }

    @PostMapping
    public String getQuestion(
            @ModelAttribute("questionNum") String questionNum, @ModelAttribute Task task, Model model){;
        System.out.println("getQuestion with questionNum, without formName");
        System.out.println(task);
        System.out.println(questionNum);
        int taskId = (Integer.parseInt(questionNum));

        Task saver = this.taskList.getTaskList().get(task.getId());
//save student answer
        System.out.println("saver: " + saver.toString());
        saver.setResult(task.getResult());
        saver.setStudentAnswer(task.getStudentAnswer());

        Task updtTask = this.taskList.getTaskList().get(taskId);
//update task in model
        System.out.println("updtTask: " + updtTask.toString());
        task.setQuestion(updtTask.getQuestion());
        task.setAnswersList(updtTask.getAnswersList());
        task.setType(updtTask.getType());
        task.setId(updtTask.getId());
        task.setCorrectAnswer(updtTask.getCorrectAnswer());
        task.setResult(updtTask.getResult());

        System.out.println(task);
        System.out.println("get question task" + task.toString());
        System.out.println(model.toString());
        System.out.println(this.taskList.getTaskList().toString());
        return "redirect:/theme";
    }

//    @PostMapping("/form")
//    public String processForm(@ModelAttribute("questionNum") String taskNum) {
//        System.out.println("Task number: " + taskNum);
//        return "redirect:/";
//    }
}
