package com.example.springinaction.repetit.testTask;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;

@Data
public class User {

    private String userAgent;

    private ArrayList<Task> usersQuestionsList;

    private Task currentTask;

    public static final Comparator<User> COMPARE_BY_USER_AGENT = new Comparator<User>() {
        @Override
        public int compare(User lhs, User rhs) {
            return lhs.getUserAgent().equals(rhs.getUserAgent()) ? 1 : 0;
        }
    };
}
