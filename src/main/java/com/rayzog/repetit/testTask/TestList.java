package com.rayzog.repetit.testTask;

import java.util.*;

import com.rayzog.repetit.dao.TaskRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class TestList {


        private List<Task> taskList;

        private final TaskRepository taskRepository;

        @Autowired
        public TestList(TaskRepository taskRepository) {
            this.taskRepository = taskRepository;
        }

        public ArrayList getQuestionList(int count){
//      count [1;4]
            ArrayList<Task> tasks = this.taskRepository.findQuestionsByThemeID();
            ArrayList<Task> questionList = new ArrayList<>();
            Set<Integer> idSet = new HashSet<>();
            Task newTask;
            int maxId = 3;
            Random random = new Random();
            int randomNum;
            while (idSet.size() < count) {
                System.out.println("size: " + idSet.size());
                randomNum = random.nextInt(maxId+1);
                System.out.println(randomNum);
                if (!idSet.contains(randomNum)){
                    idSet.add(randomNum);
                    newTask = tasks.get(randomNum).clone();
                    questionList.add(newTask);
                    newTask.setShowingId(questionList.size());
                }
            }
            Collections.sort(questionList, Task.COMPARE_BY_SHOWING_ID);



            return questionList;
        }
    @Override
    public String toString() {
        String res = "";
        for (Task task:
             taskList) {
            res = res + task.toString() + "\n";
        }
        return res;
    }

}
