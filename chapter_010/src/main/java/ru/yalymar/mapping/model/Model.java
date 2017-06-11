package ru.yalymar.mapping.model;

public class Model {

    private int id;
    private String model;
    private Manufactor manuf;

    public Model() {
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
