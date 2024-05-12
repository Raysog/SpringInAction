package com.rayzog.repetit.dao;

import com.rayzog.repetit.testTask.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskReader implements TaskRepository {

    private JdbcTemplate jdbcTemplate;

    public TaskReader(JdbcTemplate jdbcTemplate) {
        System.out.println("method construct TaskReaderSQLite");
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ArrayList<Task> findQuestionsByThemeID() {

        System.out.println("method findAll");

        Iterable<Task> queryResult = jdbcTemplate.query("select QUESTION_ID, QUESTION, TYPE from QUESTION_LIST inner join DICT_QUESTION_TYPE on QUESTION_LIST.QUESTION_TYPE = DICT_QUESTION_TYPE.TYPE_ID where PUBLIC_FLG = 1 and THEME_ID = ?"
                , this::mapRowToTask
                , 1
        );

        ArrayList<Task> taskArrayList = new ArrayList<>();

        for (Task task: queryResult) {
            int questionID = task.getId();
            task.setAnswersList(this.findAnswersByQuestionID(questionID));

            List<String> studentAnswers = new ArrayList<>();
            task.setStudentAnswers(studentAnswers);

            task.setCorrectAnswer(this.findRightAnswersByQuestionID(questionID));

            taskArrayList.add(task);
        }


        System.out.println("method findAll finish");
        return taskArrayList;
    }


    private List<String> findAnswersByQuestionID(int questionID) {
        System.out.println("method findAnswersByQuestionID");

        Iterable<String> questionAnswers = jdbcTemplate.query("select ANSWER from ANSWER_LIST where QUESTION_ID = ?"
                , this::mapRowToAnswerString
                , questionID
        );

        List<String> answersList = new ArrayList<>();

        for (String answer: questionAnswers) {
            answersList.add(answer);
        }

        System.out.println("method findAll findAnswersByQuestionID");
        return answersList;
    }


    private String findRightAnswersByQuestionID(int questionID) {
        System.out.println("method findRightAnswersByQuestionID");

        Iterable<String> questionAnswers = jdbcTemplate.query("select ANSWER from ANSWER_LIST where QUESTION_ID = ? and RIGHT_ANSWER_FLG = 1 order by ANSWER"
                , this::mapRowToAnswerString
                , questionID
        );

        String correctAnswer = questionAnswers.toString();

        System.out.println("method findAll findRightAnswersByQuestionID");
        return correctAnswer;
    }

    private Task mapRowToTask(ResultSet row, int rowNum) throws SQLException{
        System.out.println("method mapRowToTask");
        return new Task(
             row.getInt("QUESTION_ID"),
             row.getString("QUESTION"),
             Task.Type.valueOf(row.getString("TYPE"))
        );
    }

    private String mapRowToAnswerString(ResultSet row, int rowNum) throws SQLException{
        System.out.println("method mapRowToTask");
        return row.getString("ANSWER");
    }

    @Override
    public Optional<Task> findById() {
        return Optional.empty();
    }

    @Override
    public Task save(Task task) {
        return null;
    }

}
