package com.example.springinaction.repetit.dao;

import com.example.springinaction.repetit.testTask.LoginForm;
import com.example.springinaction.repetit.testTask.Task;
import com.example.springinaction.repetit.testTask.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserReader {
    private JdbcTemplate jdbcTemplate;

    public UserReader(JdbcTemplate jdbcTemplate) {
        System.out.println("method construct UserReader");
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean findUserByUserLogin(LoginForm loginForm) {

        System.out.println("method findUserByCredentials");

        int findUserFlg = jdbcTemplate.queryForObject("select count(*) as FIND_USER_FLG from USER where LOGIN = ? and HASH_PASS = ?"
                , Integer.class
                , loginForm.getLogin()
                , loginForm.getPass().hashCode()
        );


        System.out.println("method findAll finish");
        return findUserFlg == 1 ? true : false;
    }
}
