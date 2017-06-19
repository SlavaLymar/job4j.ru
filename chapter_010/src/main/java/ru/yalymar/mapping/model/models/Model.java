package ru.yalymar.mapping.model.models;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
public class Model {

    private int id;
    private String model;
    private Manufactor manuf;

    public Model() {
    }

    public Model(String model, Manufactor manuf) {
        this.model = model;
        this.manuf = manuf;
    }

    public Model(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Manufactor getManuf() {
        return manuf;
    }

    public void setManuf(Manufactor manuf) {
        this.manuf = manuf;
    }
}
