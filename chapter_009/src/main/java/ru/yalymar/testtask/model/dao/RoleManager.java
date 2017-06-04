package ru.yalymar.testtask.model.dao;

import ru.yalymar.testtask.model.Address;
import ru.yalymar.testtask.model.Role;
import ru.yalymar.testtask.model.TypeOfMusic;
import ru.yalymar.testtask.model.User;
import ru.yalymar.testtask.model.db.DBManager;
import ru.yalymar.testtask.model.repo.IRepoRole;
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
public class RoleManager extends Manager<Role> implements IRepoRole{

    public RoleManager(DAOFabric daoFabric) {
        super(daoFabric);
    }

    /** create new role.
     * return ID of new role
     * @param role
     * @return int
     */
    @Override
    public int create(Role role) {
        PreparedStatement st = null;
        ResultSet gk = null;
        int id = -1;
        try {
            st = super.dbManager.getC().prepareStatement(
                            "INSERT INTO roles (role) values (?)", new String[]{"id"});
            st.setString(1, role.getRole());
            super.dbManager.getGoUpdate().goUpdate(st);

            gk = st.getGeneratedKeys();
            while (gk.next()){
                id = gk.getInt("id");
            }
            return id;
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
            return -1;
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

    /** get all roles
     * @return List
     */
    @Override
    public List<Role> getAll() {
        List<Role> result = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = super.dbManager.getC().prepareStatement(
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

    /** get role by ID
     * @param id
     * @return Role
     */
    @Override
    public Role getById(int id) {
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st =  super.dbManager.getC().prepareStatement(
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

    /** edit role by ID
     * @param id
     * @param role
     * @return int
     */
    @Override
    public int edit(int id, Role role) {
        PreparedStatement st = null;
        try {
            st = super.dbManager.getC().prepareStatement(
                            "UPDATE roles SET role = ? WHERE id = ?");
            st.setString(1, role.getRole());
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

    /** remove role by ID
     * @param id
     * @return int
     */
    @Override
    public int remove(int id) {
        PreparedStatement st = null;
        try {
            st = super.dbManager.getC().prepareStatement(
                            "DELETE FROM roles WHERE id = ?");
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

    /** get all addresses associated with a role
     * @param role
     * @return List
     */
    @Override
    public List<Address> getAddresses(Role role) {
        List<Address> result = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = super.dbManager.getC().prepareStatement(
                            "SELECT adr.id, adr.adress FROM adresses adr JOIN users u ON " +
                                    "u.adress_id = adr.id WHERE u.role_id = (SELECT " +
                                    "r.id FROM roles r WHERE r.role = ?);");
            st.setString(1, role.getRole());
            rs = super.dbManager.getGo().go(st);
            while(rs.next()){
                result.add(new Address(rs.getInt("id"),
                        rs.getString("adress")));
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

    /** get all users associated with a role
     * @param role
     * @return List
     */
    @Override
    public List<User> getUsers(Role role) {
        List<User> result = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = super.dbManager.getC().prepareStatement(
                            "SELECT u.id, u.login, u.password, u.name, u.date, r.role, adr.adress " +
                                    "from users u JOIN roles r ON u.role_id = r.id JOIN adresses adr ON " +
                                    "u.adress_id = adr.id WHERE u.role_id = (SELECT r.id FROM roles r WHERE " +
                                    "r.role = ?);");
            st.setString(1, role.getRole());
            rs = super.dbManager.getGo().go(st);
            while(rs.next()){
                User user = new User(rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getTimestamp("date"),
                        rs.getString("role"),
                        rs.getString("adress"));
                List<TypeOfMusic> types = super.daoFabric.getTypeOfMusicManager().getTypes(user.getId());
                types.forEach((type) ->{
                    user.getTypes().add(type);
                });
                result.add(user);
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


}
