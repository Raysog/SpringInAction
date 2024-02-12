package com.example.springinaction.repetit.dao;

import com.example.springinaction.repetit.testTask.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskReader implements TaskRepository{

    private JdbcTemplate jdbcTemplate;

    public TaskReader(JdbcTemplate jdbcTemplate) {

        System.out.println("method construct TaskReader");
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Task> findAll() {

        System.out.println("method findAll");
//
//        Iterable<Task> queryResult = jdbcTemplate.query("select 1 as QUESTION_ID, 'qwer' as QUESTION, 'SingleAnswer' as TYPE"
//                , this::mapRowToTask
//                );
        System.out.println(jdbcTemplate.getDataSource());
        Iterable<Task> queryResult = jdbcTemplate.query("select QUESTION_ID, QUESTION, 'SingleAnswer' as TYPE from QUESTION_LIST where PUBLIC_FLG = 1"
                , this::mapRowToTask
        );

        System.out.println("method findAll finish");
        return queryResult;
    }

    public Iterable<String> findAllTableName() {

        System.out.println("method findAllTableName");


        Iterable<String> queryResult = jdbcTemplate.query("SELECT * FROM repetitdb.sqlite_master WHERE type='table';"
                , this::mapRowToString
        );

        System.out.println("method findAllTableName finish");
        return queryResult;
    }

    private String mapRowToString(ResultSet row, int rowNum) throws SQLException{
        System.out.println("method mapRowToTask");
        return row.getString(rowNum);
    }

    private Task mapRowToTask(ResultSet row, int rowNum) throws SQLException{
        System.out.println("method mapRowToTask");
        return new Task(
             row.getInt("QUESTION_ID"),
             row.getString("QUESTION"),
             Task.Type.valueOf(row.getString("TYPE"))
        );
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
