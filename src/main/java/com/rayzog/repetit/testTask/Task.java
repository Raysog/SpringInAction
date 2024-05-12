package com.rayzog.repetit.testTask;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
public class Task {
    private String question;

    private int id;

    private int showingId;

    private List<String> answersList;

    private List<String> studentAnswers;

    private String correctAnswer;

    private Type type;

    private String result;

    public Task(int question_id, String question, Type type) {
        this.question = question;
        this.id = question_id;
        this.type = type;
    }

    public Task() {
    }

    public enum Type {
        MultyAnswer, MultyRowAnswer, SingleAnswer, SingleRowAnswer
    }

    @Override
    public Task clone() {
        Task task = new Task();
        task.setId(this.getId());
        task.setShowingId(this.getShowingId());
        task.setQuestion(this.getQuestion());
        List<String> answerList = new ArrayList<>();
        for (String answer :
                this.getAnswersList()) {
            answerList.add(answer);
        }
        task.setAnswersList(answerList);
        task.setCorrectAnswer(this.getCorrectAnswer());
        task.setStudentAnswers(this.getStudentAnswers());
        task.setType(this.getType());
        task.setResult(this.getResult());
        return task;
    }

    public static final Comparator<Task> COMPARE_BY_SHOWING_ID = new Comparator<Task>() {
        @Override
        public int compare(Task lhs, Task rhs) {
            return Integer.compare(lhs.getShowingId(), rhs.getShowingId());
        }
    };
}
