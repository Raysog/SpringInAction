package com.rayzog.repetit.dao;

import com.rayzog.repetit.testTask.LoginForm;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserAgentReader {
    private JdbcTemplate jdbcTemplate;

    public UserAgentReader(JdbcTemplate jdbcTemplate) {
        System.out.println("method construct UserReader");
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean findUserByUserLogin(LoginForm loginForm) {

        System.out.println("method findUserByCredentials");
        System.out.println(loginForm.getPass().hashCode());
        int findUserFlg = jdbcTemplate.queryForObject("select count(*) as FIND_USER_FLG from USER where LOGIN = ? and HASH_PASS = ?"
                , Integer.class
                , loginForm.getLogin()
                , loginForm.getPass().hashCode()
        );


        System.out.println("method findAll finish");
        return findUserFlg == 1 ? true : false;
    }
}
