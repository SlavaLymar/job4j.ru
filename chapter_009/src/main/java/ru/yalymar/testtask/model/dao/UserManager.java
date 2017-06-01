package ru.yalymar.testtask.model.dao;

import ru.yalymar.testtask.model.Address;
import ru.yalymar.testtask.model.Role;
import ru.yalymar.testtask.model.TypeOfMusic;
import ru.yalymar.testtask.model.User;
import ru.yalymar.testtask.model.db.DBManager;
import ru.yalymar.testtask.model.repo.IRepoUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManager extends Manager<User> implements IRepoUser{

    private DAOFabric daoFabric;

    public UserManager(DAOFabric daoFabric) {
        this.daoFabric = daoFabric;
    }

    @Override
    public int create(User user) {
        try {
            PreparedStatement st =
                    super.dbManager.getC().prepareStatement(
                            "INSERT INTO users " +
                                    "(login, password, name, date, role_id, adress_id) " +
                                    "values (?, ?, ?, ?, ?, ?)");
            st.setString(1, user.getLogin());
            st.setString(2, user.getPassword());
            st.setString(3, user.getName());
            st.setTimestamp(4, user.getDate());
            st.setInt(5, user.getRole_id());
            st.setInt(6, user.getAddress_id());
            return super.dbManager.getGoUpdate().goUpdate(st);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
            return -1;
        }
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        ResultSet rs = null;
        try {
            PreparedStatement st =
                    super.dbManager.getC().prepareStatement(
                            "SELECT * FROM users");
            rs = super.dbManager.getGo().go(st);
            while(rs.next()){
                result.add(new User(rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getTimestamp("date"),
                        rs.getInt("role_id"),
                        rs.getInt("adress_id")));
            }
            return result;
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
            return null;
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                DBManager.logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public User getById(int id) {
        ResultSet rs = null;
        try {
            PreparedStatement st =
                    super.dbManager.getC().prepareStatement(
                            "SELECT * FROM users WHERE id = ?");
            st.setInt(1, id);
            rs = super.dbManager.getGo().go(st);
            rs.next();
            return new User(rs.getInt("id"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getTimestamp("date"),
                    rs.getInt("role_id"),
                    rs.getInt("adress_id"));
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
            return null;
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                DBManager.logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public int edit(int id, User newUser) {
        int i = -1;
        int tmp = 0;
        User oldUser = this.getById(id);

        if(!oldUser.getLogin().equals(newUser.getLogin())){
            tmp = this.editColumnLogin(id, newUser.getLogin());
        }
        if(!oldUser.getPassword().equals(newUser.getPassword())){
            i = this.editColumnPassword(id, newUser.getPassword());
            if(tmp > i) i = tmp;
        }
        if(!oldUser.getName().equals(newUser.getName())){
            tmp = this.editColumnName(id, newUser.getName());
            if(tmp > i) i = tmp;
        }
        if(oldUser.getRole_id() != newUser.getRole_id()){
            tmp = this.editColumnRole(id, newUser.getRole_id());
            if(tmp > i) i = tmp;
        }
        if( oldUser.getAddress_id() != newUser.getAddress_id()){
            tmp = this.editColumnAddress(id, newUser.getAddress_id());
            if(tmp > i) i = tmp;
        }
        return i;
    }

    private int editColumnLogin(int id, String value) {
        PreparedStatement st;
        try {
            st = dbManager.getC().prepareStatement(
                    "UPDATE users SET login = ? WHERE id = ?");
            st.setString(1, value);
            st.setInt(2, id);
            return dbManager.getGoUpdate().goUpdate(st);

        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        return -1;
    }

    private int editColumnPassword(int id, String value) {
        PreparedStatement st;
        try {
            st = dbManager.getC().prepareStatement(
                    "UPDATE users SET password = ? WHERE id = ?");
            st.setString(1, value);
            st.setInt(2, id);
            return dbManager.getGoUpdate().goUpdate(st);

        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        return -1;
    }

    private int editColumnName(int id, String value) {
        PreparedStatement st;
        try {
            st = dbManager.getC().prepareStatement(
                    "UPDATE users SET name = ? WHERE id = ?");
            st.setString(1, value);
            st.setInt(2, id);
            return dbManager.getGoUpdate().goUpdate(st);

        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        return -1;
    }

    private int editColumnRole(int id, int value) {
        PreparedStatement st;
        try {
            st = dbManager.getC().prepareStatement(
                    "UPDATE users SET role_id = ? WHERE id = ?");
            st.setInt(1, value);
            st.setInt(2, id);
            return dbManager.getGoUpdate().goUpdate(st);

        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        return -1;
    }

    private int editColumnAddress(int id, int value) {
        PreparedStatement st;
        try {
            st = dbManager.getC().prepareStatement(
                    "UPDATE users SET adress_id = ? WHERE id = ?");
            st.setInt(1, value);
            st.setInt(2, id);
            return dbManager.getGoUpdate().goUpdate(st);

        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        return -1;
    }

    @Override
    public int remove(int id) {
        try {
            PreparedStatement st =
                    super.dbManager.getC().prepareStatement(
                            "DELETE FROM users WHERE id = ?");
            st.setInt(1, id);
            return super.dbManager.getGoUpdate().goUpdate(st);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
            return -1;
        }
    }

    @Override
    public int add() {
        return 0;
    }

    @Override
    public List<User> findByAddress(Address address) {
        return null;
    }

    @Override
    public List<User> findByRole(Role role) {
        return null;
    }

    @Override
    public List<User> findByTypeOfMusic(TypeOfMusic typeOfMusic) {
        return null;
    }
}
