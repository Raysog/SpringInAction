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

import java.util.*;

@Controller
@Slf4j
@SessionAttributes({"task", "taskList"})
@RequestMapping("/theme")
public class ShowThemeController {

    private ArrayList<Task> taskList;

    private Map<String, Integer> userAgentMap;
    private Task task;




    @ModelAttribute
    public void addQuestionsToModel(Model model) {

        System.out.println("addQuestionsToModelWithTask");


        if (userAgentMap == null){
            userAgentMap = new HashMap<>();
            Random random = new Random();
            int rndCnt = random.nextInt(TestList.getInstance().getTaskList().size())+1;
            rndCnt = 4;
            System.out.println(rndCnt);
            taskList = TestList.getQuestionList(rndCnt);
            System.out.println("first try");
            task = taskList.get(0).clone();
            System.out.println(taskList.size());
            for (Task tsk :
                    taskList) {
                System.out.println(tsk.toString());
            }
            model.addAttribute("task", task);
            model.addAttribute("taskList", taskList);
            System.out.println(model.toString());
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
    public String showDesignForm(@RequestHeader Map<String, String> headers){
        System.out.println("GetMapping showDesignForm");
        System.out.println(task.toString());
        System.out.println();
        String userAgent = headers.get("user-agent");
        if (userAgentMap.containsKey(userAgent)) {
            userAgentMap.put(userAgent, userAgentMap.get(userAgent)+1);
        } else {
            userAgentMap.put(userAgent, 1);
        }
        for (String key :
                userAgentMap.keySet()) {
            System.out.println(key + ": " + userAgentMap.get(key));
        }

        System.out.println("_______________________________________________");
        return "theme";
    }

    @PostMapping("/checkAnswer")
    public String checkAnswer(
            @ModelAttribute Task task){
        System.out.println("CheckAnswer");


        if (task.getCorrectAnswer().equals(task.getStudentAnswers().toString())) {
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
        System.out.println(task);
        System.out.println(questionNum);
        int taskShowingId = (Integer.parseInt(questionNum));

        Task saver = taskList.stream()
                                .filter(taskToSave -> taskToSave.getId() == task.getId())
                                .findAny()
                                .orElse(null);
//save student answer
        System.out.println("saver: " + saver.toString());
        saver.setResult(task.getResult());
        saver.setStudentAnswers(task.getStudentAnswers());

        Task updtTask = taskList.stream()
                                .filter(taskById -> taskById.getShowingId() == taskShowingId)
                                .findAny()
                                .orElse(null);
//update task in model
        System.out.println("updtTask: " + updtTask.toString());
        task.setQuestion(updtTask.getQuestion());
        task.setAnswersList(updtTask.getAnswersList());
        task.setType(updtTask.getType());
        task.setId(updtTask.getId());
        task.setStudentAnswers(updtTask.getStudentAnswers());
        task.setShowingId(updtTask.getShowingId());
        task.setCorrectAnswer(updtTask.getCorrectAnswer());
        task.setResult(updtTask.getResult());

        System.out.println(task);
        System.out.println("get question task" + task.toString());
        System.out.println(model.toString());
        System.out.println(this.taskList.toString());
        return "redirect:/theme";
    }

}
