package ru.yalymar.crudservlet.model;

import ru.yalymar.crudservlet.db.DBManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class UserManager {

    private final DBManager dbManager = new DBManager();

    public int add(User user) {
        PreparedStatement st;
        try {
            st = dbManager.getC().prepareStatement(
                    "INSERT INTO users (name, login, dateCreate) VALUES (?, ?, ?)");
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setTimestamp(3, new Timestamp(user.getCreateDate().getTimeInMillis()));
            return dbManager.getGoUpdate().goUpdate(st);

        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        return -1;
    }

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
        return null;
    }

    public ResultSet getAll(){
        PreparedStatement st;
        try {
            st = dbManager.getC().prepareStatement(
                    "SELECT * FROM users");
            return dbManager.getGo().go(st);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        return null;
    }

    public int edit(String id, User newUser){
        User oldUser = null;
        ResultSet rs = this.get(id);
        try {
            Calendar c = Calendar.getInstance();
            rs.next();
            c.setTimeInMillis(rs.getTimestamp("dateCreate").getTime());
            oldUser = new User(rs.getString("name"), rs.getString("login"),
                    rs.getString("email"), c);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        if(oldUser.getName() != newUser.getName()){
            return this.editColumnName(id, newUser.getName());
        }
        if(oldUser.getLogin() != newUser.getLogin()){
            return this.editColumnLogin(id, newUser.getLogin());
        }
        if(oldUser.getEmail() != newUser.getEmail()){
            return this.editColumnEmail(id, newUser.getEmail());
        }
        return -1;
    }

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

    public String print(ResultSet rs){
        String str1 = String.format("%1$-5s%2$-10s%3$-10s%4$-30s%5$-20s%6$s", "id", "name", "login", "email", "dateCreate",
                System.getProperty("line.separator"));
        String str2 = null;
        try {
            rs.next();
            str2 = String.format("%1$-5s%2$-10s%3$-10s%4$-30s%5$-20s%6$s", rs.getInt("id"),
                    rs.getString("name"), rs.getString("login"),
                    rs.getString("email"), rs.getTimestamp("dateCreate").toString(),
                    System.getProperty("line.separator"));
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        String result = str1.concat(str2);
        return result;
    }

    public String printAll(){
        ResultSet rs = this.getAll();
        String str1 = String.format("%1$-5s%2$-10s%3$-10s%4$-30s%5$-20s%6$s", "id", "name", "login", "email", "dateCreate",
                System.getProperty("line.separator"));

        try {
            while (rs.next()) {
                String str2 = null;
                try {
                    str2 = String.format("%1$-5s%2$-10s%3$-10s%4$-10s%5$-10s%6$s", rs.getInt("id"),
                            rs.getString("name"), rs.getString("login"),
                            rs.getString("email"), rs.getTimestamp("dateCreate").toString(),
                            System.getProperty("line.separator"));
                } catch (SQLException e) {
                    DBManager.logger.error(e.getMessage(), e);
                }
                str1.concat(str2);
            }
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        return str1;
    }
}
