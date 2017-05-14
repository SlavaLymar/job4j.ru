package ru.yalymar.crudservlet;

import java.util.Calendar;

public class User {

    private String name;
    private String login;
    private String email;
    private Calendar createDate;

    public User(String name, String login, String email, Calendar createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }
}
