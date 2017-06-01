package ru.yalymar.testtask.model.dao;

import ru.yalymar.testtask.model.Address;
import ru.yalymar.testtask.model.Role;
import ru.yalymar.testtask.model.User;
import ru.yalymar.testtask.model.db.DBManager;
import ru.yalymar.testtask.model.repo.IRepoRole;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleManager extends Manager<Role> implements IRepoRole{

    @Override
    public int create(Role role) {
        try {
            PreparedStatement st =
                    super.dbManager.getC().prepareStatement(
                            "INSERT INTO roles (role) values (?)");
            st.setString(1, role.getRole());
            return super.dbManager.getGoUpdate().goUpdate(st);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
            return -1;
        }
    }

    @Override
    public List<Role> getAll() {
        List<Role> result = new ArrayList<>();
        ResultSet rs = null;
        try {
            PreparedStatement st =
                    super.dbManager.getC().prepareStatement(
                            "SELECT * FROM roles");
            rs = super.dbManager.getGo().go(st);
            while(rs.next()){
                result.add(new Role(rs.getInt("id"), rs.getString("role")));
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
    public Role getById(int id) {
        ResultSet rs = null;
        try {
            PreparedStatement st =
                    super.dbManager.getC().prepareStatement(
                            "SELECT * FROM roles WHERE id = ?");
            st.setInt(1, id);
            rs = super.dbManager.getGo().go(st);
            rs.next();
            return new Role(rs.getInt("id"), rs.getString("role"));
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
    public int edit(int id, Role role) {
        try {
            PreparedStatement st =
                    super.dbManager.getC().prepareStatement(
                            "UPDATE roles SET role = ? WHERE id = ?");
            st.setString(1, role.getRole());
            st.setInt(2, id);
            return super.dbManager.getGoUpdate().goUpdate(st);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
            return -1;
        }
    }

    @Override
    public int remove(int id) {
        try {
            PreparedStatement st =
                    super.dbManager.getC().prepareStatement(
                            "DELETE FROM roles WHERE id = ?");
            st.setInt(1, id);
            return super.dbManager.getGoUpdate().goUpdate(st);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
            return -1;
        }
    }

    @Override
    public List<Address> getAddresses() {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }
}
