package ru.yalymar.testtask.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class User {

    private int id;
    private String name;
    private String login;
    private String password;
    private Timestamp date;
    private String role;
    private String address;
    private Set<TypeOfMusic> types;

    public User(String login, String password, String name,
                Timestamp date, String role, String address) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.date = date;
        this.role = role;
        this.address = address;
        this.types = new HashSet<>();
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
        this.types = new HashSet<>();
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

    public Set<TypeOfMusic> getTypes() {
        return types;
    }
}
