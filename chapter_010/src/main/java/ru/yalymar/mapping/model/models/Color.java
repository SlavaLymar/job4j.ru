package ru.yalymar.mapping.model.models;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
public class Color {

    private int id;
    private String color;

    public Color() {
    }

    public Color(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
