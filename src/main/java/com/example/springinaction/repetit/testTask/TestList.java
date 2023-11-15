package com.example.springinaction.repetit.testTask;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import com.example.springinaction.repetit.testTask.Task;

public class TestList {

    private List<Task> taskList = new ArrayList<>();

    public void addTask(Task task){
        this.taskList.add(task);
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
