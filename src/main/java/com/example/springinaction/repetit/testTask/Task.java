package com.example.springinaction.repetit.testTask;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Task {
    private String question;

    private int id;

    private List<String> answersList;

    private String studentAnswer;

    private String correctAnswer;

    private Type type;

    private String result;

    public enum Type {
        MultyAnswer, MultyRowAnswer, SingleAnswer, SingleRowAnswer
    }

    @Override
    protected Task clone() {
        Task task = new Task();
        task.setId(this.getId());
        task.setQuestion(this.getQuestion());
        List<String> answerList = new ArrayList<>();
        for (String answer :
                this.getAnswersList()) {
            answerList.add(answer);
        }
        task.setAnswersList(answerList);
        task.setCorrectAnswer(this.getCorrectAnswer());
        task.setType(this.getType());
        return task;
    }
}
