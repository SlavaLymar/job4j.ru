package ru.yalymar.testtask.model;

/**
 * @author slavalymar
 * @version 1
 * @since 04.06.2017
 */
public class Role {

    private int id;
    private String role;

    public Role(String role) {
        this.role = role;
    }

    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
