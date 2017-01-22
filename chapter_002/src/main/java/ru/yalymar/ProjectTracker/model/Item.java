package ru.yalymar.model;


import ru.yalymar.tracker.Tracker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author slavalymar
 * @since 13.01.2017
 * @version 1
 */
public class Item {

    private String name;
    private String description;
    private Date time;
    private String id;
    private List <Comment> comments;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.time = new Date();
        this.comments = new ArrayList<Comment>();
    }

    public Item(String name, String description, String id) {
        this.name = name;
        this.description = description;
        this.time = new Date();
        this.id = id;
        this.comments = new ArrayList<Comment>();
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Date getTime() {
        return time;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o){
        Item i = (Item) o;
        return (this.getId() == i.getId());
    }

    /**
     * @return int
     */
     @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (description == null ? 0 : description.hashCode());
        result = prime * result + (id == null ? 0 : id.hashCode());
        return result;
    }

    public void addToListComments(Comment comment){
        comments.add(comment);
    }


}
