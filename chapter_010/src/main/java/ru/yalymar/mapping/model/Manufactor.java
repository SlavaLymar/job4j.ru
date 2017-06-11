package ru.yalymar.mapping.model;

public class Manufactor {

    private int id;
    private String manuf;

    public Manufactor() {
    }

    public Manufactor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManuf() {
        return manuf;
    }

    public void setManuf(String manuf) {
        this.manuf = manuf;
    }
}
