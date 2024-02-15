package com.example.springinaction.repetit;

import com.example.springinaction.Taco;
import com.example.springinaction.TacoOrder;
import com.example.springinaction.repetit.dao.TaskRepository;
import com.example.springinaction.repetit.testTask.TestList;
import com.example.springinaction.repetit.testTask.Task;
import com.example.springinaction.repetit.testTask.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.example.springinaction.repetit.testTask.Task.Type;

import java.util.*;

@Controller
@Slf4j
@SessionAttributes({"question", "taskList"})
@RequestMapping("/theme")
public class ShowThemeController {

    private ArrayList<Task> taskList;

    private Map<String, User> userMap;
    private Task task;

    private final TaskRepository taskRepository;

    @Autowired
    public ShowThemeController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }


    @ModelAttribute
    public void addQuestionsToModel(Model model, @RequestHeader Map<String, String> headers) {
        System.out.println("___________________________________________________");
        System.out.println("addQuestionsToModelWithTask");
        String userAgent = headers.get("user-agent");
        System.out.println(userAgent);

        User currentUser = null;

        System.out.println("-----------------------------------------------");
        System.out.println("DB read");

//            Iterable<String> tasks = taskRepository.findAllTableName();
//            System.out.println(tasks.toString());
//            for (String task :
//                    tasks) {
//                System.out.println(task.toString());
//            }

        Iterable<Task> tasks = taskRepository.findQuestionsByThemeID();
        System.out.println(tasks.toString());
        for (Task task :
                tasks) {
            System.out.println(task.toString());
        }

        System.out.println("-----------------------------------------------");

        if (userMap == null) {
            userMap = new HashMap<>();



        }

        if (!userMap.containsKey(userAgent)){
            System.out.println("first try for user: " + userAgent);
            Random random = new Random();
            int rndCnt = random.nextInt(TestList.getInstance().getTaskList().size())+1;
            rndCnt = 4;
            System.out.println(rndCnt);
            User newUser = new User();
            newUser.setUserAgent(userAgent);
            newUser.setUsersQuestionsList(TestList.getQuestionList(rndCnt));
            newUser.setCurrentTask(newUser.getUsersQuestionsList().get(0).clone());
            currentUser = newUser;
            userMap.put(userAgent, newUser);
//

        }
        else {
            currentUser = userMap.get(userAgent);
            System.out.println("not first try for user: " + userAgent);
        }
        this.task = currentUser.getCurrentTask().clone();
        this.taskList = currentUser.getUsersQuestionsList();

        model.addAttribute("question", this.task);
        model.addAttribute("taskList", this.taskList);

        System.out.println(model.toString());
        System.out.println("___________________________________________________");

    }

    @ModelAttribute(name = "taskList")
    public ArrayList<Task> taskList(){
        System.out.println("Create new taskList");
        if (this.taskList == null){
            System.out.println("return new taskList");
            return new ArrayList<>();
        } else {
            System.out.println("return current taskList");
            return this.taskList;
        }
    }

    @ModelAttribute(name = "question")
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
        System.out.println("_______________________________________________");
        System.out.println("GetMapping showDesignForm");
        System.out.println(task.toString());
        System.out.println();
        String userAgent = headers.get("user-agent");
        for (Task taskForPrint :
                this.taskList) {
            System.out.println(taskForPrint.toString());
        }
        System.out.println("_______________________________________________");
        return "theme";
    }

    @PostMapping("/checkAnswer")
    public String checkAnswer(
            @ModelAttribute("question") Task task, @RequestHeader Map<String, String> headers){
        System.out.println("CheckAnswer");
        System.out.println("task from front: " + task.toString());
        String userAgent = headers.get("user-agent");
        System.out.println(userAgent);
        User user = this.userMap.get(userAgent);
        Task userCurrentTask = user.getCurrentTask();
        for (String ans :
                task.getStudentAnswers()) {
            System.out.println("user answer: " + ans);
        }
        if (userCurrentTask.getCorrectAnswer().equals(task.getStudentAnswers().toString())) {
            System.out.println("right answer");
            userCurrentTask.setResult("yes");
            userCurrentTask.setStudentAnswers(task.getStudentAnswers());
        } else {
            System.out.println("wrong answer");
            userCurrentTask.setResult("no");
            userCurrentTask.setStudentAnswers(task.getStudentAnswers());
        }
        System.out.println("after update user task: " + userCurrentTask.toString());
        this.task = userCurrentTask.clone();
        System.out.println(this.task);
//        log.info("Processing taco: {}", task);

        return "redirect:/theme";
    }

    @PostMapping
    public String getQuestion(
            @ModelAttribute("questionNum") String questionNum, @ModelAttribute("question") Task task, Model model, @RequestHeader Map<String, String> headers){;
        System.out.println(task);
        System.out.println(questionNum);


        String userAgent = headers.get("user-agent");
        User user = userMap.get(userAgent);
        ArrayList<Task> userQuestionList = user.getUsersQuestionsList();

        int taskShowingId = (Integer.parseInt(questionNum));

        Task saver = userQuestionList.stream()
                                .filter(taskToSave -> taskToSave.getId() == task.getId())
                                .findAny()
                                .orElse(null);
//save student answer
        System.out.println("saver: " + saver.toString());
        saver.setResult(task.getResult());
        saver.setStudentAnswers(task.getStudentAnswers());
        for (Task taskForPrint :
                this.taskList) {
            System.out.println(taskForPrint.toString());
        }
        Task showingTask = userQuestionList.stream()
                                .filter(taskById -> taskById.getShowingId() == taskShowingId)
                                .findAny()
                                .orElse(null);
//update showing task in model
        user.setCurrentTask(showingTask);
        System.out.println("updtTask: " + showingTask.toString());
        this.task.setQuestion(showingTask.getQuestion());
        this.task.setAnswersList(showingTask.getAnswersList());
        this.task.setType(showingTask.getType());
        this.task.setId(showingTask.getId());
        this.task.setStudentAnswers(showingTask.getStudentAnswers());
        this.task.setShowingId(showingTask.getShowingId());
        this.task.setCorrectAnswer(showingTask.getCorrectAnswer());
        this.task.setResult(showingTask.getResult());

        this.taskList = userQuestionList;

        System.out.println("get question task" + this.task.toString());
        for (Task taskForPrint :
                this.taskList) {
            System.out.println(taskForPrint.toString());
        }
        System.out.println(model.toString());
        return "redirect:/theme";
    }

}
