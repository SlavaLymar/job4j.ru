package ru.yalymar.crudservlet.model;

import ru.yalymar.crudservlet.db.DBManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class UserManager {

    public int add(DBManager dbManager, User user) {
        PreparedStatement st = null;
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

    public ResultSet get(DBManager dbManager, String id){
        PreparedStatement st = null;
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

    public ResultSet getAll(DBManager dbManager){
        PreparedStatement st = null;
        try {
            st = dbManager.getC().prepareStatement(
                    "SELECT * FROM users");
            return dbManager.getGo().go(st);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        return null;
    }

    public int edit(DBManager dbManager, String id, User newUser){
        User oldUser = null;
        ResultSet rs = this.get(dbManager, id);
        try {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(rs.getTimestamp("dateCreate").getTime());
            oldUser = new User(rs.getString("name"), rs.getString("login"),
                    rs.getString("email"), c);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        if(oldUser.getName() != newUser.getName()){
            return this.editColumn(dbManager, id, "name", newUser.getName());
        }
        if(oldUser.getLogin() != newUser.getLogin()){
            return this.editColumn(dbManager, id, "login", newUser.getLogin());
        }
        if(oldUser.getEmail() != newUser.getEmail()){
            return this.editColumn(dbManager, id, "email", newUser.getEmail());
        }
        return -1;
    }

    private int editColumn(DBManager dbManager, String id, String key, String value) {
        PreparedStatement st = null;
        try {
            st = dbManager.getC().prepareStatement(
                    "UPDATE users SET ? = ?");
            st.setString(1, key);
            st.setString(2, value);
            return dbManager.getGoUpdate().goUpdate(st);

        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        return -1;
    }

    public int delete(DBManager dbManager, String id){
        PreparedStatement st = null;
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
        String str1 = String.format("%1$-5s%2$-10s%3$-10s%4$-10s%5$-10s\n", "id", "name", "login", "email", "dateCreate");
        String str2 = null;
        try {
            str2 = String.format("%1$-5s%2$-10s%3$-10s%4$-10s%5$-10s\n", rs.getInt("id"),
                    rs.getString("name"), rs.getString("login"),
                    rs.getString("email"), rs.getTimestamp("dateCreate").toString());
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        return str1.concat(str2);
    }

    public String printAll(ResultSet rs){
        String str1 = String.format("%1$-5s%2$-10s%3$-10s%4$-10s%5$-10s\n", "id", "name", "login", "email", "dateCreate");

        try {
            while (rs.next()) {
                String str2 = null;
                try {
                    str2 = String.format("%1$-5s%2$-10s%3$-10s%4$-10s%5$-10s\n", rs.getInt("id"),
                            rs.getString("name"), rs.getString("login"),
                            rs.getString("email"), rs.getTimestamp("dateCreate").toString());
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
