package ru.yalymar.ioc.storages.models;

public class Role {

    private int id;
    private String role;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role='" + role + '\'' +
                '}';
    }
}
