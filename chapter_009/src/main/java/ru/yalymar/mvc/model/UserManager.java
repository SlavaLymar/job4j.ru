package ru.yalymar.mvc.model;

import ru.yalymar.mvc.model.db.DBManager;

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
                    "INSERT INTO users (name, login, email, dateCreate) VALUES (?, ?, ?, ?)");
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
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
                    "SELECT * FROM users WHERE id = ?");
            st.setInt(1, Integer.parseInt(id));
            rs = dbManager.getGo().go(st);
            rs.next();
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(rs.getTimestamp("datecreate").getTime());
            return new User(id,
                    rs.getString("name"),
                    rs.getString("login"),
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
                    "SELECT * FROM users");
            rs = dbManager.getGo().go(st);
            while(rs.next()){
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(rs.getTimestamp("datecreate").getTime());
                users.add(new User(String.valueOf(rs.getInt("id")),
                        rs.getString("name"),
                        rs.getString("login"),
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
            oldUser = new User(rs.getString("name"), rs.getString("login"),
                    rs.getString("email"), c);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        int tmp;
        if(oldUser.getName() != req.getParameter("name")){
            i = this.editColumnName(id, req.getParameter("name"));
        }
        if(oldUser.getLogin() != req.getParameter("login")){
            tmp = this.editColumnLogin(id, req.getParameter("login"));
            if(tmp > i) i = tmp;
        }
        if(oldUser.getEmail() != req.getParameter("email")){
            tmp = this.editColumnEmail(id, req.getParameter("email"));
            if(tmp > i) i = tmp;
        }
        return i;
    }

    public ResultSet getRS(String id) {
        PreparedStatement st;
        try {
            st = dbManager.getC().prepareStatement(
                    "SELECT * FROM users WHERE id = ?");
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
                    "UPDATE users SET email = ? WHERE id = ?");
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
                    "UPDATE users SET login = ? WHERE id = ?");
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
        return -1;
    }

}
