package ru.yalymar.filter.model.db;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author slavalymar
 * @version 1
 * @since 02.05.2017
 */
public class DBManager {

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

    public static final Logger logger = Logger.getLogger(DBManager.class);

    public DBManager() {
        this.initGoUpdate();
        this.initGo();
        this.connectDB();
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
            } finally {
                if (st != null) {
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
     * get connection to database
     *
     * @return Connection
     */
    public Connection connectDB() {
        BasicDataSource source = new BasicDataSource();
        source.setDriverClassName("org.postgresql.Driver");
        source.setUsername("postgres");
        source.setPassword("lymar123");
        source.setUrl("jdbc:postgresql://localhost:5432/postgres");

        try {
            return this.c = source.getConnection();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * disconnect database
     */
    public void disconnectDB() {
        if (this.c != null) {
            try {
                this.c.close();
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public void createUsersTable() throws SQLException {
        this.go.go(this.c.prepareStatement(
                "CREATE TABLE Users (" +
                        "id SERIAL NOT NULL PRIMARY KEY, " +
                        "login CHAR(100), " +
                        "password CHAR(100), " +
                        "email CHAR(100), " +
                        "dateCreate TIMESTAMP," +
                        "country CHAR(100), " +
                        "city CHAR(100)," +
                        "role CHAR(10) DEFAULT 'admin')"
        ));
    }

    public void dropUsersTable() throws SQLException {
        this.go.go(this.c.prepareStatement("DROP TABLE Users"));
    }

}
