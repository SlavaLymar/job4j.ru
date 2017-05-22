package ru.yalymar.filter.model;

import ru.yalymar.filter.model.db.DBManager;
import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
                    "INSERT INTO users1 (login, password, email, dateCreate) VALUES (?, ?, ?, ?)");
            st.setString(1, user.getLogin());
            st.setString(2, user.getPassword());
            st.setString(3, user.getEmail());
            st.setTimestamp(4, new Timestamp(user.getCreateDate().getTimeInMillis()));
            return dbManager.getGoUpdate().goUpdate(st);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        return -1;
    }

    /** get user from db
     * @param id
     * @return ResultSet
     */
    public User get(String id){
        PreparedStatement st;
        ResultSet rs = null;
        try {
            st = dbManager.getC().prepareStatement(
                    "SELECT * FROM users1 WHERE id = ?;");
            st.setInt(1, Integer.parseInt(id));
            rs = dbManager.getGo().go(st);
            rs.next();
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(rs.getTimestamp("datecreate").getTime());
            return new User(id,
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("email"),
                    c);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                DBManager.logger.error(e.getMessage(), e);
            }
        }
        return null;
    }

    /** get user from db
     * @param login
     * @param password
     * @return ResultSet
     */
    public User getByLoginPassword(String login, String password){
        PreparedStatement st;
        ResultSet rs = null;
        try {
            st = dbManager.getC().prepareStatement(
                    "SELECT * FROM users1 WHERE login = ? AND password = ?");
            st.setString(1, login);
            st.setString(2, password);
            rs = dbManager.getGo().go(st);
            rs.next();
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(rs.getTimestamp("datecreate").getTime());
            return new User(
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("email"),
                    c);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                DBManager.logger.error(e.getMessage(), e);
            }
        }
        return null;
    }

    /** get all users from db
     * @return ResultSet
     */
    public List<User> getAll(){
        PreparedStatement st;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        try {
            st = dbManager.getC().prepareStatement(
                    "SELECT * FROM users1");
            rs = dbManager.getGo().go(st);
            while(rs.next()){
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(rs.getTimestamp("datecreate").getTime());
                users.add(new User(String.valueOf(rs.getInt("id")),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("email"),
                        c));
            }
            return users;
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        finally {
            try {
                rs.close();
            } catch (SQLException e) {
                DBManager.logger.error(e.getMessage(), e);
            }
        }
        return null;
    }

    /** edit user in db
     * @param req
     * @return int
     */
    public int edit(HttpServletRequest req){
        User oldUser = null;
        String id = req.getParameter("id");
        ResultSet rs = this.getRS(id);
        int i = -1;
        try {
            Calendar c = Calendar.getInstance();
            rs.next();
            c.setTimeInMillis(rs.getTimestamp("dateCreate").getTime());
            oldUser = new User(rs.getString("login"), rs.getString("password"),
                    rs.getString("email"), c);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        int tmp = 0;
        if(!oldUser.getLogin().equals(req.getParameter("login"))){
            tmp = this.editColumnLogin(id, req.getParameter("login"));
        }
        if(!oldUser.getPassword().equals(req.getParameter("password"))){
            i = this.editColumnPassword(id, req.getParameter("password"));
            if(tmp > i) i = tmp;
        }
        if(!oldUser.getEmail().equals(req.getParameter("email"))){
            tmp = this.editColumnEmail(id, req.getParameter("email"));
            if(tmp > i) i = tmp;
        }
        return i;
    }

    public ResultSet getRS(String id) {
        PreparedStatement st;
        try {
            st = dbManager.getC().prepareStatement(
                    "SELECT * FROM users1 WHERE id = ?");
            st.setInt(1, Integer.parseInt(id));
            return dbManager.getGo().go(st);

        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        return null;
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
                    "UPDATE users1 SET email = ? WHERE id = ?");
            st.setString(1, value);
            st.setInt(2, Integer.parseInt(id));
            return dbManager.getGoUpdate().goUpdate(st);

        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
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
                    "UPDATE users1 SET login = ? WHERE id = ?");
            st.setString(1, value);
            st.setInt(2, Integer.parseInt(id));
            return dbManager.getGoUpdate().goUpdate(st);

        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        return -1;
    }

    /** edit user`s name
     * @param id
     * @param value
     * @return int
     */
    private int editColumnPassword(String id, String value) {
        PreparedStatement st;
        try {
            st = dbManager.getC().prepareStatement(
                    "UPDATE users1 SET password = ? WHERE id = ?");
            st.setString(1, value);
            st.setInt(2, Integer.parseInt(id));
            return dbManager.getGoUpdate().goUpdate(st);

        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
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
                    "DELETE FROM users1 WHERE id = ?");
            st.setInt(1, Integer.parseInt(id));
            return dbManager.getGoUpdate().goUpdate(st);

        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        return -1;
    }

    public boolean isValid(String login, String password){
        if(this.getByLoginPassword(login, password) != null){
            return true;
        }
        return false;
    }
}
