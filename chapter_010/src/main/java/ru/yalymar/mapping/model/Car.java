package ru.yalymar.mapping.model;

public class Car {

    private int id;
    private Manufactor manuf;
    private Model model;
    private Transmission transmission;
    private Body body;
    private Color color;

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Manufactor getManuf() {
        return manuf;
    }

    public void setManuf(Manufactor manuf) {
        this.manuf = manuf;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
