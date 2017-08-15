package ru.yalymar.mvc.model.models;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
public class Body {

    private int id;
    private String body;

    public Body() {
    }

    public Body(int id) {
        this.id = id;
    }

    public Body(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
