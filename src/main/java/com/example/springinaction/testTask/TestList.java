package com.example.springinaction.testTask;


import com.example.springinaction.Taco;

import java.util.ArrayList;
import java.util.List;

public class TestList {

    private List<TestTask> taskList = new ArrayList<>();

    public void addTask(TestTask task){
        this.taskList.add(task);
    }

}
