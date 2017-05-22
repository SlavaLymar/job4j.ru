package ru.yalymar.filter.model;

import java.util.Calendar;

/**
 * @author slavalymar
 * @since 15.05.2017
 * @version 1
 */
public class User {

    private String id;
    private String name;
    private String login;
    private String email;
    private Calendar createDate;

    public User(String id, String name, String login, String email, Calendar createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public User(String name, String login, String email, Calendar createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public String getId() {
        return id;
    }
}
