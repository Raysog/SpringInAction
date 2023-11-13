package com.example.springinaction.testTask;

import lombok.Data;

import java.util.List;

@Data
public class MultyAnswerTask implements TestTask{
    private String question;

    private List<String> answersList;

    private String studentAnswer;

    private String correctAnswer;
}
