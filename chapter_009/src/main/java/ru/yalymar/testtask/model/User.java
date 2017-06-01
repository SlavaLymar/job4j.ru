package ru.yalymar.testtask.model;

import java.sql.Timestamp;
import java.util.Calendar;

public class User {

    private int id;
    private String name;
    private String login;
    private String password;
    private Timestamp date;
    private String role;
    private String address;

    public User(String login, String password, String name,
                Timestamp date, String role, String address) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.date = date;
        this.role = role;
        this.address = address;
    }

    public User(int id, String login, String password, String name,
                Timestamp date, String role, String address) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.date = date;
        this.role = role;
        this.address = address;
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

    public String getRole() {
        return role;
    }

    public String getAddress() {
        return address;
    }
}
