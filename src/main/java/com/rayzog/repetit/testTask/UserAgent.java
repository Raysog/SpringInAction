package com.rayzog.repetit.testTask;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;

@Data
public class UserAgent {

    private String userAgent;

    private ArrayList<Task> usersQuestionsList;

    private Task currentTask;

    public static final Comparator<UserAgent> COMPARE_BY_USER_AGENT = new Comparator<UserAgent>() {
        @Override
        public int compare(UserAgent lhs, UserAgent rhs) {
            return lhs.getUserAgent().equals(rhs.getUserAgent()) ? 1 : 0;
        }
    };
}
