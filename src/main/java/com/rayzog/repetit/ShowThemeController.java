package com.rayzog.repetit;

import com.rayzog.repetit.dao.TaskRepository;
import com.rayzog.repetit.testTask.TestList;
import com.rayzog.repetit.testTask.Task;
import com.rayzog.repetit.testTask.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@Slf4j
@SessionAttributes({"question", "taskList"})
@RequestMapping("/theme")
public class ShowThemeController {

    private ArrayList<Task> taskList;

    private Map<String, UserAgent> userMap;
    private Task task;
    private TestList testList;

    @Autowired
    public ShowThemeController(TaskRepository taskRepository){
        //this.taskRepository = taskRepository;
        this.testList = new TestList(taskRepository);
    }


    @ModelAttribute
    public void addQuestionsToModel(Model model, @RequestHeader Map<String, String> headers) {
        System.out.println("___________________________________________________");
        System.out.println("addQuestionsToModelWithTask");
        String userAgentHeader = headers.get("user-agent");
        System.out.println(userAgentHeader);

        UserAgent currentUserAgent = null;

        System.out.println("-----------------------------------------------");
        System.out.println("DB read");

//            Iterable<String> tasks = taskRepository.findAllTableName();
//            System.out.println(tasks.toString());
//            for (String task :
//                    tasks) {
//                System.out.println(task.toString());
//            }

       // Iterable<Task> tasks = taskRepository.findQuestionsByThemeID();


        System.out.println("-----------------------------------------------");

        if (userMap == null) {
            userMap = new HashMap<>();
        }

        if (!userMap.containsKey(userAgentHeader)){
            System.out.println("first try for user: " + userAgentHeader);
            Random random = new Random();
            //int rndCnt = random.nextInt(testList.getTaskList().size())+1;
            int rndCnt = 4;
            System.out.println(rndCnt);
            UserAgent newUserAgent = new UserAgent();
            newUserAgent.setUserAgent(userAgentHeader);
            newUserAgent.setUsersQuestionsList(testList.getQuestionList(rndCnt));
            newUserAgent.setCurrentTask(newUserAgent.getUsersQuestionsList().get(0).clone());
            currentUserAgent = newUserAgent;
            userMap.put(userAgentHeader, newUserAgent);

            System.out.println(newUserAgent.getUsersQuestionsList().toString());
            for (Task task :
                    newUserAgent.getUsersQuestionsList()) {
                System.out.println(task.toString());
            }

        }
        else {
            currentUserAgent = userMap.get(userAgentHeader);
            System.out.println("not first try for user: " + userAgentHeader);
        }
        this.task = currentUserAgent.getCurrentTask().clone();
        this.taskList = currentUserAgent.getUsersQuestionsList();

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
        System.out.println(userAgent);
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
        String userAgentHeader = headers.get("user-agent");
        System.out.println(userAgentHeader);
        UserAgent userAgent = this.userMap.get(userAgentHeader);
        Task userCurrentTask = userAgent.getCurrentTask();
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


        String userAgentHeader = headers.get("user-agent");
        UserAgent userAgent = userMap.get(userAgentHeader);
        ArrayList<Task> userQuestionList = userAgent.getUsersQuestionsList();

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
        userAgent.setCurrentTask(showingTask);
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
