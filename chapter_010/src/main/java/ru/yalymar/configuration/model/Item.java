package ru.yalymar.configuration.model;

import java.sql.Timestamp;

/**
 * @author slavalymar
 * @since 13.06.2017
 * @version 1
 */
public class Item {

    private int id;
    private String description;
    private Timestamp created;
    private boolean done;

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
