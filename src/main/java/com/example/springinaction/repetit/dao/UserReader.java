//package com.example.springinaction.repetit.dao;
//
//import com.example.springinaction.repetit.testTask.Task;
//import com.example.springinaction.repetit.testTask.User;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class UserReader {
//    private JdbcTemplate jdbcTemplate;
//
//    public UserReader(JdbcTemplate jdbcTemplate) {
//
//        System.out.println("method construct UserReader");
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public User findUserByUserAgent(String userAgent) {
//
//        System.out.println("method findAll");
//
//        Iterable<Task> queryResult = jdbcTemplate.query("select QUESTION_ID, QUESTION, TYPE from QUESTION_LIST inner join DICT_QUESTION_TYPE on QUESTION_LIST.QUESTION_TYPE = DICT_QUESTION_TYPE.TYPE_ID where PUBLIC_FLG = 1 and THEME_ID = ?"
//                , this::mapRowToUser
//                , 1
//        );
//
//        ArrayList<Task> taskArrayList = new ArrayList<>();
//
//        for (Task task: queryResult) {
//            int questionID = task.getId();
//            task.setAnswersList(this.findAnswersByQuestionID(questionID));
//
//            List<String> studentAnswers = new ArrayList<>();
//            task.setStudentAnswers(studentAnswers);
//
//            task.setCorrectAnswer(this.findRightAnswersByQuestionID(questionID));
//
//            taskArrayList.add(task);
//        }
//
//
//        System.out.println("method findAll finish");
//        return taskArrayList;
//    }
//
//    private Task mapRowToUser(ResultSet row, int rowNum) throws SQLException {
//        System.out.println("method mapRowToTask");
//        return new Task(
//                row.getInt("QUESTION_ID"),
//                row.getString("QUESTION"),
//                Task.Type.valueOf(row.getString("TYPE"))
//        );
//    }
//}
