package com.example.springinaction.repetit.dao;

import com.example.springinaction.repetit.testTask.Task;

import java.util.Optional;

public interface TaskRepository {
    Iterable<Task> findAll();
    Optional<Task> findById();
    Task save(Task task);

    Iterable<String> findAllTableName();
}
