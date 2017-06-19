package ru.yalymar.mapping.model.models;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
public class Role {

    private int id;
    private String role;

    public Role() {
    }

    public Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
