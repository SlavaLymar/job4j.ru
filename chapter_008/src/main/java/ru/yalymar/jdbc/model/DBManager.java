package ru.yalymar.jdbc.model;

import org.apache.log4j.Logger;
import ru.yalymar.jdbc.tracker.Tracker;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author slavalymar
 * @since 02.05.2017
 * @version 1
 */
public class DBManager {

    /**
     * create query interface
     */
    private GoCreate goCreate;

    /**
     * update query interface
     */
    private GoUpdate goUpdate;

    /**
     * select query interface
     */
    private Go go;

    /**
     * connect to database
     */
    private Connection c;

    /**
     * properties
     */
    private Properties properties = new Properties();

    private static final Logger logger = Logger.getLogger(Tracker.class);

    public DBManager() {
        this.initProperties();
        this.initGoCreate();
        this.initGoUpdate();
        this.initGo();
    }

    public Connection getC() {
        return this.c;
    }

    public GoUpdate getGoUpdate() {
        return this.goUpdate;
    }

    public Go getGo() {
        return this.go;
    }

    /**
     * initialized go interface
     */
    private void initGo() {
        this.go = (st) -> {
            try {
                if (st != null) {
                    return st.executeQuery();
                }
                return null;
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
                return null;
            }
        };
    }

    /**
     * initialized goCreate interface
     */
    private void initGoCreate() {
        this.goCreate = (s) -> {
            PreparedStatement st = null;
            try {
                if(!this.c.isClosed()) {
                    st = this.c.prepareStatement(s);
                }
                if (st != null) {
                    return st.execute();
                }
                return false;
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
        };
    }

    /**
     * initialized goUpdate interface
     */
    private void initGoUpdate() {
        this.goUpdate = (st) -> {
            try {
                if (st != null) {
                    return st.executeUpdate();
                }
                return -1;
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
                return -1;
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
        };
    }

    /**
     * initialized properties
     */
    private void initProperties() {
        try(FileInputStream in = new FileInputStream(
                "C:/Java/job4j.ru/chapter_008/resources/resources.properties")) {

            this.properties.load(in);
        }
        catch(IOException e){
            logger.error(e.getMessage(), e);
        }
    }

    /** create database
     * @return boolean
     */
    public boolean createDB(){
        String url = this.properties.getProperty("urlCreate");
        String login = this.properties.getProperty("login");
        String password = this.properties.getProperty("password");
        PreparedStatement st = null;
        try {
            this.c = DriverManager.getConnection(url, login, password);
            st = this.c.prepareStatement(this.properties.getProperty("CREATE_DB"));
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

    /** get connection to database
     * @return Connection
     */
    public Connection connectDB(){
        try(FileInputStream in = new FileInputStream(
                "D:/dstr/job4j.ru/chapter_008/src/main/resources/resources.properties")) {

            this.properties.load(in);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        String url = this.properties.getProperty("urlConnect");
        String login = this.properties.getProperty("login");
        String password = this.properties.getProperty("password");
        try {
            return this.c = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            this.disconnectDB();
        }
        return null;
    }

    /**
     * disconnect database
     */
    public void disconnectDB(){
        if (this.c != null) {
            try {
                this.c.close();
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    /** create items` table
     * @return boolean
     */
    public boolean createItemsTable(){
        return this.goCreate.goCreate(this.properties.getProperty("CREATE_ITEMS_TABLE"));
    }

    /** create comments` table
     * @return boolean
     */
    public boolean createCommentsTable(){
        return this.goCreate.goCreate(this.properties.getProperty("CREATE_COMMENTS_TABLE"));
    }

    public boolean dropItemsTable() {
        return this.goCreate.goCreate(this.properties.getProperty("DELETE_ITEMS_TABLE"));
    }

    public boolean dropCommentsTable() {
        return this.goCreate.goCreate(this.properties.getProperty("DELETE_COMMENTS_TABLE"));
    }

}
