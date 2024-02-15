package com.example.springinaction.repetit.dao;

import com.example.springinaction.repetit.testTask.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Iterable<Task> findQuestionsByThemeID();



    Optional<Task> findById();
    Task save(Task task);

}
