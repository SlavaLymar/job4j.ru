package ru.yalymar.testtask.model;

import java.sql.Timestamp;
import java.util.Calendar;

public class User {

    private int id;
    private String name;
    private String login;
    private String password;
    private Timestamp date;

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.date = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    public User(int id, String name, String login, String password, Timestamp date) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Timestamp getDate() {
        return date;
    }
}
