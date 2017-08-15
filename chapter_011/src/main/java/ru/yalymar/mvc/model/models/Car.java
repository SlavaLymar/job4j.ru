package ru.yalymar.mvc.model.models;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
public class Car {

    private int id;
    private Model model;
    private Transmission transmission;
    private Body body;
    private Color color;

    public Car() {
    }

    public Car(int id) {
        this.id = id;
    }

    public Car(Model model, Transmission transmission,
               Body body, Color color) {
        this.model = model;
        this.transmission = transmission;
        this.body = body;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
