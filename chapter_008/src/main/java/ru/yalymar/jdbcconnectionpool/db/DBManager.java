package ru.yalymar.jdbcconnectionpool.db;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author slavalymar
 * @since 11.05.2017
 * @version 1
 */
public class DBManager {


    private int i;
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

    private static final Logger logger = Logger.getLogger(DBManager.class);

    public DBManager(int i) {
        this.i = i;
        this.initProperties();
        this.initGoUpdate();
        this.initGo();
        this.connectDB();
    }

    public int getI() {
        return this.i;
    }

    public Connection getC() {
        return this.c;
    }

    public static Logger getLogger() {
        return logger;
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
        Class c = this.getClass();
        InputStream inputStream = c.getResourceAsStream("/a.properties");
        try {
            this.properties.load(inputStream);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

    }

    /** get connection to database
     * @return Connection
     */
    public Connection connectDB(){
        String url = this.properties.getProperty("urlConnectPool");
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

}
