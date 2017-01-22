package ru.yalymar.tracker;

import ru.yalymar.model.Comment;
import ru.yalymar.model.Item;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author slavalymar
 * @since 13.01.2017
 * @version 1
 */
public class Tracker {

    private List <Item> items = new ArrayList<Item>();
    private static final Random RANDOM = new Random();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
    private int idCounter = 0;

    /**
     * @param item
     * @return new item
     */
    public Item add(Item item){
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * @param item
     */
    public void update(Item item){
        int index = this.items.indexOf(item);
        if(index != -1) {
            Collections.replaceAll(this.items, this.items.get(index), item);
        }
    }

    /**
     * @param item
     */
    public void delete(Item item){
            if(this.items.indexOf(item) != -1)
                this.items.remove(this.items.indexOf(item));
    }

    /**
     * @return List<Item> results
     */
    public List <Item> findAll(){
        List <Item> result = new ArrayList<Item>();
        for(Item i: this.items) {
            if (i!=null) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * @return List<Item> results
     */
    public List <Item> findByName(String key){
        List <Item> result = new ArrayList<Item>();
        for(Item i: this.items){
            if(i!= null && i.getName().equals(key)){
                result.add(i);
            }
        }
        return result;
    }

    /**
     * @param id
     * @param comment
     */
    public void addCommentById(String id, Comment comment){
        Item item = this.findById(id);
        item.addToListComments(comment);
    }

    /**
     * @param name
     * @param comment
     */
    public void addCommentByName(String name, Comment comment){
        List<Item> item = this.findByName(name);
        for(Item i: item){
            i.addToListComments(comment);
        }
    }

    /**
     * @return id
     */
    public String generateId(){

        return String.valueOf(this.idCounter++);
    }

    /**
     * @param id
     * @return item
     */
    public Item findById(String id){
        Item result = null;
        for(Item i: this.items){
            if(i != null && i.getId().equals(id)){
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * @param desc
     * @return List<Item> result
     */
    public List <Item> findByDescription(String desc){
        List <Item> result = new ArrayList<Item>();
        for(Item i: this.items){
            if(i!= null && i.getDescription().equals(desc)){
                result.add(i);
            }
        }
        return result;
    }

    /**
     * @return List<Item> items
     */
    public List <Item> getItems() {
        return items;
    }

    /**
     * show all items
     */
    public void showAllItems(){
        System.out.printf("%1$-30s%2$-30s%3$-30s%4$-20s\n", "Id", "Name", "Description", "Date");
        System.out.printf("--------------------------------------------------------------------------------------------------------------\n");
        for(Item i: this.items){
            if(i != null) {
                System.out.printf("%1$-30s%2$-30s%3$-30s%4$-20s\n", i.getId(), i.getName(), i.getDescription(), sdf.format(i.getTime()));
            }
        }
    }

    /**
     * @param item
     */
    public void showAllItems(List<Item> item){
        System.out.printf("%1$-30s%2$-30s%3$-30s%4$-20s\n", "Id", "Name", "Description", "Date");
        System.out.printf("--------------------------------------------------------------------------------------------------------------\n");
        for(Item i: item){
            if(i != null) {
                System.out.printf("%1$-30s%2$-30s%3$-30s%4$-20s\n", i.getId(), i.getName(), i.getDescription(), sdf.format(i.getTime()));
            }
        }
    }

    /**
     * @param item
     */
    public void showOneItems(Item item){
        System.out.printf("%1$-30s%2$-30s%3$-30s%4$-20s\n", "Id", "Name", "Description", "Date");
        System.out.printf("--------------------------------------------------------------------------------------------------------------\n");
        if(item!=null)
        System.out.printf("%1$-30s%2$-30s%3$-30s%4$-20s\n", item.getId(), item.getName(), item.getDescription(), sdf.format(item.getTime()));
    }

    /**
     * @param id
     */
    public void showComments(String id){
        Item item = this.findById(id);
        List<Comment> commentsItem = item.getComments();
        int position = 1;
        for (Comment c: commentsItem){
            System.out.printf("%1$-3d%2$-50s%3$-20s\n", position++, c.getComment(), sdf.format(item.getTime()));
        }
    }

}
