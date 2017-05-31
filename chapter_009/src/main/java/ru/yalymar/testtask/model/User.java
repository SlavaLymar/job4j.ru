package ru.yalymar.testtask.model;

import java.sql.Timestamp;
import java.util.Calendar;

public class User {

    private int id;
    private String name;
    private String login;
    private String password;
    private Timestamp date;
    private int role_id;
    private int address_id;

    public User(String login, String password, String name, int role_id, int address_id) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.date = new Timestamp(Calendar.getInstance().getTimeInMillis());
        this.role_id = role_id;
        this.address_id = address_id;
    }

    public User(int id, String login, String password, String name, Timestamp date,
                int role_id, int address_id) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.date = date;
        this.role_id = role_id;
        this.address_id = address_id;
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

    public int getRole_id() {
        return role_id;
    }

    public int getAddress_id() {
        return address_id;
    }
}
