package ru.yalymar.crudservlet.model;

import ru.yalymar.crudservlet.model.db.DBManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * @author slavalymar
 * @since 15.05.2017
 * @version 1
 */
public class UserManager {

    /**
     * instance of db manager
     */
    private final DBManager dbManager = new DBManager();

    /** add user to db
     * @param user
     * @return int
     */
    public int add(User user) {
        PreparedStatement st;
        try {
            st = dbManager.getC().prepareStatement(
                    "INSERT INTO users (name, login, email, dateCreate) VALUES (?, ?, ?, ?)");
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setTimestamp(4, new Timestamp(user.getCreateDate().getTimeInMillis()));
            return dbManager.getGoUpdate().goUpdate(st);

        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        } finally {
            this.dbManager.disconnectDB();
        }
        return -1;
    }

    /** get user from db
     * @param id
     * @return ResultSet
     */
    public ResultSet get(String id){
        PreparedStatement st;
        try {
            st = dbManager.getC().prepareStatement(
                    "SELECT * FROM users WHERE id = ?");
            st.setInt(1, Integer.parseInt(id));
            return dbManager.getGo().go(st);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        finally {
            this.dbManager.disconnectDB();
        }
        return null;
    }

    /** get all users from db
     * @return ResultSet
     */
    public ResultSet getAll(){
        PreparedStatement st;
        try {
            st = dbManager.getC().prepareStatement(
                    "SELECT * FROM users");
            return dbManager.getGo().go(st);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        finally {
            this.dbManager.disconnectDB();
        }
        return null;
    }

    /** edit user in db
     * @param id
     * @param newUser
     * @return int
     */
    public int edit(String id, User newUser){
        User oldUser = null;
        ResultSet rs = this.get(id);
        int i = -1;
        try {
            Calendar c = Calendar.getInstance();
            rs.next();
            c.setTimeInMillis(rs.getTimestamp("dateCreate").getTime());
            oldUser = new User(rs.getString("name"), rs.getString("login"),
                    rs.getString("email"), c);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        int tmp;
        if(oldUser.getName() != newUser.getName()){
            i = this.editColumnName(id, newUser.getName());
        }
        if(oldUser.getLogin() != newUser.getLogin()){
            tmp = this.editColumnLogin(id, newUser.getLogin());
            if(tmp > i) i = tmp;
        }
        if(oldUser.getEmail() != newUser.getEmail()){
            tmp = this.editColumnEmail(id, newUser.getEmail());
            if(tmp > i) i = tmp;
        }
        return i;
    }

    /** edit user`s email
     * @param id
     * @param value
     * @return int
     */
    private int editColumnEmail(String id, String value) {
        PreparedStatement st;
        try {
            st = dbManager.getC().prepareStatement(
                    "UPDATE users SET email = ? WHERE id = ?");
            st.setString(1, value);
            st.setInt(2, Integer.parseInt(id));
            return dbManager.getGoUpdate().goUpdate(st);

        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        finally {
            this.dbManager.disconnectDB();
        }
        return -1;
    }

    /** edit user`s login
     * @param id
     * @param value
     * @return int
     */
    private int editColumnLogin(String id, String value) {
        PreparedStatement st;
        try {
            st = dbManager.getC().prepareStatement(
                    "UPDATE users SET login = ? WHERE id = ?");
            st.setString(1, value);
            st.setInt(2, Integer.parseInt(id));
            return dbManager.getGoUpdate().goUpdate(st);

        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        finally {
            this.dbManager.disconnectDB();
        }
        return -1;
    }

    /** edit user`s name
     * @param id
     * @param value
     * @return int
     */
    private int editColumnName(String id, String value) {
        PreparedStatement st;
        try {
            st = dbManager.getC().prepareStatement(
                    "UPDATE users SET name = ? WHERE id = ?");
            st.setString(1, value);
            st.setInt(2, Integer.parseInt(id));
            return dbManager.getGoUpdate().goUpdate(st);

        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        finally {
            this.dbManager.disconnectDB();
        }
        return -1;
    }

    /** delete user from db
     * @param id
     * @return int
     */
    public int delete(String id){
        PreparedStatement st;
        try {
            st = dbManager.getC().prepareStatement(
                    "DELETE FROM users WHERE id = ?");
            st.setInt(1, Integer.parseInt(id));
            return dbManager.getGoUpdate().goUpdate(st);

        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        finally {
            this.dbManager.disconnectDB();
        }
        return -1;
    }


}
