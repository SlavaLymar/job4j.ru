package ru.yalymar.testtask.model.dao;

import ru.yalymar.testtask.model.Address;
import ru.yalymar.testtask.model.Role;
import ru.yalymar.testtask.model.db.DBManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressManager extends Manager<Address> {

    private DAOFabric daoFabric;

    public AddressManager(DAOFabric daoFabric) {
        this.daoFabric = daoFabric;
    }

    @Override
    public int create(Address address) {
        try {
            PreparedStatement st =
                    super.dbManager.getC().prepareStatement(
                            "INSERT INTO adresses (adress) values (?)");
            st.setString(1, address.getAddress());
            return super.dbManager.getGoUpdate().goUpdate(st);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
            return -1;
        }
    }

    @Override
    public List<Address> getAll() {
        List<Address> result = new ArrayList<>();
        ResultSet rs = null;
        try {
            PreparedStatement st =
                    super.dbManager.getC().prepareStatement(
                            "SELECT * FROM adresses");
            rs = super.dbManager.getGo().go(st);
            while(rs.next()){
                result.add(new Address(rs.getInt("id"), rs.getString("adress")));
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
    public Address getById(int id) {
        ResultSet rs = null;
        try {
            PreparedStatement st =
                    super.dbManager.getC().prepareStatement(
                            "SELECT * FROM adresses WHERE id = ?");
            st.setInt(1, id);
            rs = super.dbManager.getGo().go(st);
            rs.next();
            return new Address(rs.getInt("id"), rs.getString("adress"));
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
    public int edit(int id, Address address) {
        try {
            PreparedStatement st =
                    super.dbManager.getC().prepareStatement(
                            "UPDATE adresses SET adress = ? WHERE id = ?");
            st.setString(1, address.getAddress());
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
                            "DELETE FROM adresses WHERE id = ?");
            st.setInt(1, id);
            return super.dbManager.getGoUpdate().goUpdate(st);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
            return -1;
        }
    }
}
