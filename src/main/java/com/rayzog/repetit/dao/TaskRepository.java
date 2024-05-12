package com.rayzog.repetit.dao;

import com.rayzog.repetit.testTask.Task;

import java.util.ArrayList;
import java.util.Optional;

public interface TaskRepository {
    ArrayList<Task> findQuestionsByThemeID();

    Optional<Task> findById();
    Task save(Task task);

}
