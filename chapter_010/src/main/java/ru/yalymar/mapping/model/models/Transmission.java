package ru.yalymar.mapping.model.models;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
public class Transmission {

    private int id;
    private String name;

    public Transmission() {
    }

    public Transmission(int id) {
        this.id = id;
    }

    public Transmission(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
