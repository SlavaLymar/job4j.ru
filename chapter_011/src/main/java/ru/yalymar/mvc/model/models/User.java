package ru.yalymar.mvc.model.models;

import java.sql.Timestamp;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
public class User {

    private int id;
    private String login;
    private String password;
    private String name;
    private Timestamp create;
    private Role role;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(String login, String password, String name, Timestamp create, Role role) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.create = create;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreate() {
        return create;
    }

    public void setCreate(Timestamp create) {
        this.create = create;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

