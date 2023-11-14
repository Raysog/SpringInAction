package com.example.springinaction.repetit.testTask;

import lombok.Data;

import java.util.List;

@Data
public class Task {
    private String question;

    private List<String> answersList;

    private String studentAnswer;

    private String correctAnswer;

    private Type type;

    public enum Type {
        MultyAnswer, MultyRowAnswer, SingleAnswer, SingleRowAnswer
    }
}
