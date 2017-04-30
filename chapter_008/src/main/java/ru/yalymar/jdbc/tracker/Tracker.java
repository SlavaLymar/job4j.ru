package ru.yalymar.jdbc.tracker;

import org.apache.log4j.Logger;
import ru.yalymar.jdbc.model.Comment;
import ru.yalymar.jdbc.model.Item;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author slavalymar
 * @since 13.01.2017
 * @version 1
 */
public class Tracker {

    private Connection conn = null;
    private Properties props = new Properties();
    private static final Logger logger = Logger.getLogger(Tracker.class);
    private static final Random RANDOM = new Random();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
    private int idCounter = 0;

    public Connection getConn() {
        return this.conn;
    }

    public boolean createDB(){
        try(FileInputStream in = new FileInputStream(
                "C:/Java/job4j.ru/chapter_008/resources/resources.properties")) {

            props.load(in);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        String url = this.props.getProperty("urlCreate");
        String login = this.props.getProperty("login");
        String password = this.props.getProperty("password");
        PreparedStatement st = null;
        try {
            this.conn = DriverManager.getConnection(url, login, password);
            st = this.conn.prepareStatement("CREATE DATABASE Tracker;");
            return st.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            this.disconnectDB();
            return false;
        }
        finally {
            if(st != null){
                try {
                    st.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    public Connection connectDB(){
        try(FileInputStream in = new FileInputStream(
                "C:/Java/job4j.ru/chapter_008/resources/resources.properties")) {

            props.load(in);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        String url = this.props.getProperty("urlConnect");
        String login = this.props.getProperty("login");
        String password = this.props.getProperty("password");
        try {
            return this.conn = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            this.disconnectDB();
        }
        return null;
    }

    public void disconnectDB(){
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public boolean createItemsTable(){
        PreparedStatement st = null;
        try {
            if(!this.conn.isClosed()){
                st = this.conn.prepareStatement("CREATE TABLE Items(?, ?, ?);");

                return st.execute();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        finally {
            if(st != null){
                try {
                    st.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return false;
    }

    /*
    public Item add(Item item){
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }


    public void update(Item item){
        int index = this.items.indexOf(item);
        if(index != -1) {
            Collections.replaceAll(this.items, this.items.get(index), item);
        }
    }

    public void delete(Item item){
            if(this.items.indexOf(item) != -1)
                this.items.remove(this.items.indexOf(item));
    }

    public List <Item> findAll(){
        List <Item> result = new ArrayList<Item>();
        for(Item i: this.items) {
            if (i!=null) {
                result.add(i);
            }
        }
        return result;
    }

    public List <Item> findByName(String key){
        List <Item> result = new ArrayList<Item>();
        for(Item i: this.items){
            if(i!= null && i.getName().equals(key)){
                result.add(i);
            }
        }
        return result;
    }

    public void addCommentById(String id, Comment comment){
        Item item = this.findById(id);
        item.addToListComments(comment);
    }

    public void addCommentByName(String name, Comment comment){
        List<Item> item = this.findByName(name);
        for(Item i: item){
            i.addToListComments(comment);
        }
    }

    public String generateId(){

        return String.valueOf(this.idCounter++);
    }

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

    public List <Item> findByDescription(String desc){
        List <Item> result = new ArrayList<Item>();
        for(Item i: this.items){
            if(i!= null && i.getDescription().equals(desc)){
                result.add(i);
            }
        }
        return result;
    }

    public List <Item> getItems() {
        return items;
    }

    public void showAllItems(){
        System.out.printf("%1$-30s%2$-30s%3$-30s%4$-20s\n", "Id", "Name", "Description", "Date");
        System.out.printf("--------------------------------------------------------------------------------------------------------------\n");
        for(Item i: this.items){
            if(i != null) {
                System.out.printf("%1$-30s%2$-30s%3$-30s%4$-20s\n", i.getId(), i.getName(), i.getDescription(), sdf.format(i.getTime()));
            }
        }
    }

    public void showAllItems(List<Item> item){
        System.out.printf("%1$-30s%2$-30s%3$-30s%4$-20s\n", "Id", "Name", "Description", "Date");
        System.out.printf("--------------------------------------------------------------------------------------------------------------\n");
        for(Item i: item){
            if(i != null) {
                System.out.printf("%1$-30s%2$-30s%3$-30s%4$-20s\n", i.getId(), i.getName(), i.getDescription(), sdf.format(i.getTime()));
            }
        }
    }

    public void showOneItems(Item item){
        System.out.printf("%1$-30s%2$-30s%3$-30s%4$-20s\n", "Id", "Name", "Description", "Date");
        System.out.printf("--------------------------------------------------------------------------------------------------------------\n");
        if(item!=null)
        System.out.printf("%1$-30s%2$-30s%3$-30s%4$-20s\n", item.getId(), item.getName(), item.getDescription(), sdf.format(item.getTime()));
    }

    public void showComments(String id){
        Item item = this.findById(id);
        List<Comment> commentsItem = item.getComments();
        int position = 1;
        for (Comment c: commentsItem){
            System.out.printf("%1$-3d%2$-50s%3$-20s\n", position++, c.getComment(), sdf.format(item.getTime()));
        }
    }

*/
}
