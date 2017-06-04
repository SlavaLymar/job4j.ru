package ru.yalymar.testtask.model.dao;

import ru.yalymar.testtask.model.Address;
import ru.yalymar.testtask.model.db.DBManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @version 1
 * @since 04.06.2017
 */
public class AddressManager extends Manager<Address> {

    public AddressManager(DAOFabric daoFabric) {
        super(daoFabric);
    }

    /** add address into database.
     * return id of the record
     * @param address
     * @return int
     */
    @Override
    public int create(Address address) {
        PreparedStatement st = null;
        ResultSet gk = null;
        int id = -1;
        try {
            st = super.dbManager.getC().prepareStatement(
                            "INSERT INTO adresses (adress) values (?)", new String[]{"id"});
            st.setString(1, address.getAddress());
            super.dbManager.getGoUpdate().goUpdate(st);

            gk = st.getGeneratedKeys();
            while (gk.next()){
                id = gk.getInt("id");
            }
            return id;
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
            return id;
        }
        finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (gk != null) {
                    gk.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /** get all addresses
     * @return List
     */
    @Override
    public List<Address> getAll() {
        List<Address> result = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = super.dbManager.getC().prepareStatement(
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
                if (st != null) {
                    st.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                DBManager.logger.error(e.getMessage(), e);
            }
        }
    }

    /** get address by ID
     * @param id
     * @return Address
     */
    @Override
    public Address getById(int id) {
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = super.dbManager.getC().prepareStatement(
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
                if (st != null) {
                    st.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                DBManager.logger.error(e.getMessage(), e);
            }
        }
    }

    /** edit address by ID
     * @param id
     * @param address
     * @return int
     */
    @Override
    public int edit(int id, Address address) {
        PreparedStatement st = null;
        try {
            st = super.dbManager.getC().prepareStatement(
                            "UPDATE adresses SET adress = ? WHERE id = ?");
            st.setString(1, address.getAddress());
            st.setInt(2, id);
            return super.dbManager.getGoUpdate().goUpdate(st);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
            return -1;
        }
        finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /** remove address by ID
     * @param id
     * @return int
     */
    @Override
    public int remove(int id) {
        PreparedStatement st = null;
        try {
            st = super.dbManager.getC().prepareStatement(
                            "DELETE FROM adresses WHERE id = ?");
            st.setInt(1, id);
            return super.dbManager.getGoUpdate().goUpdate(st);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
            return -1;
        }
        finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
