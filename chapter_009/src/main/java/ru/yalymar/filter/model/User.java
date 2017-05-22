package ru.yalymar.filter.model;

import java.util.Calendar;

/**
 * @author slavalymar
 * @since 15.05.2017
 * @version 1
 */
public class User {

    private String id;
    private String password;
    private String login;
    private String email;
    private Calendar createDate;

    public User(String id, String login,String password, String email, Calendar createDate) {
        this.id = id;
        this.password = password;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public User(String login, String password, String email, Calendar createDate) {
        this.password = password;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public String getPassword() {
        return password;
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
