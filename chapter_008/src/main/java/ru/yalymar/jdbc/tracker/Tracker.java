package ru.yalymar.jdbc.tracker;

import org.apache.log4j.Logger;
import ru.yalymar.jdbc.model.DBManager;
import ru.yalymar.jdbc.model.Item;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author slavalymar
 * @since 13.01.2017
 * @version 1
 */
public class Tracker {

    private static final Random RANDOM = new Random();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
    private int idCounter = 0;
    private DBManager dbManager;
    private static final Logger logger = Logger.getLogger(Tracker.class);

    public Tracker(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public int add(Item item){
        PreparedStatement st = null;
        try {
            st = this.dbManager.getC().prepareStatement(
                    "INSERT INTO Items (name, description, date) values (?, ?, ?)");

            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            st.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            return this.dbManager.getGoUpdate().goUpdate(st);
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
        return -2;
    }


    public int update(Item item){
        PreparedStatement st = null;
        try {
            st = this.dbManager.getC().prepareStatement(
                    "UPDATE Items SET name = ?, description = ? where id = ?");

            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            st.setInt(3, Integer.parseInt(item.getId()));
            return this.dbManager.getGoUpdate().goUpdate(st);
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
        return -2;
    }


    public int delete(Item item){
        PreparedStatement st = null;
        try {
            st = this.dbManager.getC().prepareStatement(
                    "DELETE FROM Items where id = ?");

            st.setInt(1, Integer.parseInt(item.getId()));
            return this.dbManager.getGoUpdate().goUpdate(st);
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
        return -2;
    }

    /*
    public List <Item> findAll(){
        List <Item> result = new ArrayList<Item>();
        for(Item i: this.items) {
            if (i!=null) {
                result.add(i);
            }
        }
        return result;
    }

*/
    public List<Item> findByName(String key){
        List <Item> result = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = this.dbManager.getC().prepareStatement("SELECT * FROM Items where name = ?");
            st.setString(1, key);
            rs = this.dbManager.getGo().go(st);
            while (rs.next()){
                result.add(new Item(Integer.toString(rs.getInt("id")),
                        rs.getString("name").trim(),
                        rs.getString("description").trim(),
                        rs.getTimestamp("date")));
            }
            return result;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        finally {
            if(st != null){
                try {
                    st.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /*

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

*/
    public Item findById(String id){
        Item result = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = this.dbManager.getC().prepareStatement("SELECT * FROM Items where id = ?");
            st.setInt(1, Integer.parseInt(id));
            rs = this.dbManager.getGo().go(st);
            while (rs.next()){
                result = new Item(Integer.toString(rs.getInt("id")),
                        rs.getString("name").trim(),
                        rs.getString("description").trim(),
                        rs.getTimestamp("date"));
            }
            return result;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        finally {
            if(st != null){
                try {
                    st.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    public List <Item> findByDescription(String desc){
        List <Item> result = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = this.dbManager.getC().prepareStatement("SELECT * FROM Items where description = ?");
            st.setString(1, desc);
            rs = this.dbManager.getGo().go(st);
            while (rs.next()){
                result.add(new Item(Integer.toString(rs.getInt("id")),
                        rs.getString("name").trim(),
                        rs.getString("description").trim(),
                        rs.getTimestamp("date")));
            }
            return result;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        finally {
            if(st != null){
                try {
                    st.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    public boolean showAllItems(){
        System.out.printf("%1$-30s%2$-30s%3$-30s%4$-20s\n", "Id", "Name", "Description", "Date");
        System.out.printf("--------------------------------------------------------------------------------------------------------------\n");

        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = this.dbManager.getC().prepareStatement("SELECT * FROM Items");
            rs = this.dbManager.getGo().go(st);
            while (rs.next()){
                System.out.printf("%1$-30d%2$-30s%3$-30s%4$-20s\n", rs.getInt("id"),
                        rs.getString("name").trim(), rs.getString("description").trim(),
                        sdf.format(rs.getTimestamp("date")));
            }
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
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
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
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

    public void showOneItem(Item item){
        System.out.printf("%1$-30s%2$-30s%3$-30s%4$-20s\n", "Id", "Name", "Description", "Date");
        System.out.printf("--------------------------------------------------------------------------------------------------------------\n");
        if(item!=null) {
            System.out.printf("%1$-30s%2$-30s%3$-30s%4$-20s\n", item.getId(), item.getName(), item.getDescription(), sdf.format(item.getTime()));
        }
    }

    /*
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
