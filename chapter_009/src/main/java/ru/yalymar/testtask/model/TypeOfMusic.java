package ru.yalymar.testtask.model;

public class TypeOfMusic {

    private int id;
    private String type;

    public TypeOfMusic(String type) {
        this.type = type;
    }

    public TypeOfMusic(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
