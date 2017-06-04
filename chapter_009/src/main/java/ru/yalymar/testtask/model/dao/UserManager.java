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

    public UserManager(DAOFabric daoFabric) {
        super(daoFabric);
    }

    @Override
    public int create(User user) {
        int id = -1;
        PreparedStatement st = null;
        PreparedStatement stType = null;
        ResultSet gk = null;

        int idAddress = super.daoFabric.getAddressManager().
                create(new Address(user.getAddress()));
        if(idAddress > 0) {
            try {
                st = super.dbManager.getC().prepareStatement(
                                "INSERT INTO users (login, password, name, date, role_id, adress_id)" +
                                        "VALUES (?, ?, ?, ?, (SELECT r.id FROM roles r WHERE r.role = ?), ?);",
                                            new String[]{"id"});
                st.setString(1, user.getLogin());
                st.setString(2, user.getPassword());
                st.setString(3, user.getName());
                st.setTimestamp(4, user.getDate());
                st.setString(5, user.getRole());
                st.setInt(6, idAddress);
                super.dbManager.getGoUpdate().goUpdate(st);

                gk = st.getGeneratedKeys();
                if(gk.next()){
                    id = gk.getInt("id");
                }

                if(id != -1) {
                    for (TypeOfMusic type : user.getTypes()) {
                        stType = super.dbManager.getC().prepareStatement(
                                "INSERT INTO user_musictype (type_id, user_id) VALUES " +
                                        "((SELECT t.id from musictypes t WHERE t.type = ?), ?);");
                        stType.setString(1, type.getType());
                        stType.setInt(2, id);
                        super.dbManager.getGoUpdate().goUpdate(stType);
                    }
                }

                return id;
            } catch (SQLException e) {
                DBManager.logger.error(e.getMessage(), e);
            }
            finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                    if (stType != null) {
                        stType.close();
                    }
                    if (gk != null) {
                        gk.close();
                    }
                } catch (SQLException e) {
                    DBManager.logger.error(e.getMessage(), e);
                }
            }
        }
        return -1;
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = super.dbManager.getC().prepareStatement(
                            "SELECT u.id, u.login, u.password, u.name, u.date, " +
                                    "r.role, adr.adress FROM users u LEFT JOIN roles r ON " +
                                    "u.role_id = r.id LEFT JOIN adresses adr ON u.adress_id = adr.id;");
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

    @Override
    public User getById(int id) {
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = super.dbManager.getC().prepareStatement(
                            "SELECT u.id, u.login, u.password, u.name, u.date, " +
                                    "(SELECT role FROM roles r WHERE u.role_id = r.id), " +
                                    "adr.adress FROM users u LEFT JOIN adresses adr ON " +
                                    "u.adress_id = adr.id WHERE u.id = ?");
            st.setInt(1, id);
            rs = super.dbManager.getGo().go(st);
            rs.next();
            return new User(rs.getInt("id"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getTimestamp("date"),
                    rs.getString("role"),
                    rs.getString("adress"));
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
        if(!oldUser.getRole().equals(newUser.getRole())){
            tmp = this.editColumnRole(id, newUser.getRole());
            if(tmp > i) i = tmp;
        }
        if( !oldUser.getAddress().equals(newUser.getAddress())){
            tmp = this.editColumnAddress(id, newUser.getAddress());
            if(tmp > i) i = tmp;
        }
        return i;
    }

    private int editColumnLogin(int id, String value) {
        PreparedStatement st = null;
        try {
            st = dbManager.getC().prepareStatement(
                    "UPDATE users SET login = ? WHERE id = ?");
            st.setString(1, value);
            st.setInt(2, id);
            return dbManager.getGoUpdate().goUpdate(st);

        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
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
        return -1;
    }

    private int editColumnPassword(int id, String value) {
        PreparedStatement st = null;
        try {
            st = dbManager.getC().prepareStatement(
                    "UPDATE users SET password = ? WHERE id = ?");
            st.setString(1, value);
            st.setInt(2, id);
            return dbManager.getGoUpdate().goUpdate(st);

        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
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
        return -1;
    }

    private int editColumnName(int id, String value) {
        PreparedStatement st = null;
        try {
            st = dbManager.getC().prepareStatement(
                    "UPDATE users SET name = ? WHERE id = ?");
            st.setString(1, value);
            st.setInt(2, id);
            return dbManager.getGoUpdate().goUpdate(st);

        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
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
        return -1;
    }

    private int editColumnRole(int id, String role) {
        PreparedStatement st = null;
        try {
            st = dbManager.getC().prepareStatement(
                    "UPDATE users SET role_id = " +
                            "(SELECT r.id FROM roles r WHERE r.role = ?) WHERE id = ?;");
            st.setString(1, role);
            st.setInt(2, id);
            return dbManager.getGoUpdate().goUpdate(st);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
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
        return -1;
    }

    private int editColumnAddress(int id, String address) {
        PreparedStatement st = null;
        try {
            st = dbManager.getC().prepareStatement(
                    "UPDATE users SET adress_id = " +
                            "(SELECT adr.id FROM adress adr WHERE adr.adress = ?) WHERE id = ?;");
            st.setString(1, address);
            st.setInt(2, id);
            return dbManager.getGoUpdate().goUpdate(st);

        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
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
        return -1;
    }

    @Override
    public int remove(int id) {
        PreparedStatement st = null;
        int result = -1;
        try {
            // remove from user_musictype table
            st = super.dbManager.getC().prepareStatement(
                    "DELETE FROM user_musictype WHERE user_id = ?");
            st.setInt(1, id);
            super.dbManager.getGoUpdate().goUpdate(st);

            // remove from users table
            int adress_id = -1;
            st = super.dbManager.getC().prepareStatement(
                    "DELETE FROM users WHERE id = ?", new String[]{"adress_id"});
            st.setInt(1, id);
            result = super.dbManager.getGoUpdate().goUpdate(st);

            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()){
                adress_id = rs.getInt("adress_id");
            }
            rs.close();

            //remove from adresses table
            st = super.dbManager.getC().prepareStatement(
                    "DELETE FROM adresses adr WHERE adr.id = ?");
            st.setInt(1, adress_id);
            super.dbManager.getGoUpdate().goUpdate(st);
            return result;
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
            return result;
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

    @Override
    public int add(User user) {
        return this.create(user);
    }

    @Override
    public List<User> findByAddress(Address address) {
        ResultSet rs = null;
        List<User> result = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = super.dbManager.getC().prepareStatement(
                            "SELECT u.id, u.login, u.password, u.name, u.date, " +
                                    "(SELECT role FROM roles r WHERE u.role_id = r.id), adr.adress" +
                                    "FROM users u LEFT JOIN adresses adr ON u.adress_id = adr.id WHERE " +
                                    "adr.adress = ?");
            st.setString(1, address.getAddress());
            rs = super.dbManager.getGo().go(st);
            while (rs.next()){
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

    @Override
    public List<User> findByRole(Role role) {
        ResultSet rs = null;
        List<User> result = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = super.dbManager.getC().prepareStatement(
                            "SELECT u.id, u.login, u.password, u.name, u.date, r.role" +
                                    "(SELECT adr.adress FROM adresses adr WHERE u.adress_id = adr.id) FROM users u" +
                                    "LEFT JOIN roles r ON u.role_id = r.id WHERE r.role = ?;");
            st.setString(1, role.getRole());
            rs = super.dbManager.getGo().go(st);
            while (rs.next()){
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

    @Override
    public List<User> findByTypeOfMusic(TypeOfMusic typeOfMusic) {
        ResultSet rs = null;
        List<User> result = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = super.dbManager.getC().prepareStatement(
                    "SELECT u.id, u.login, u.password, u.name, u.date," +
                            "(SELECT r.role FROM roles r WHERE r.id = u.role_id)," +
                            "(SELECT adr.adress FROM adresses adr WHERE u.adress_id = adr.id) FROM " +
                            "users u LEFT JOIN user_musictype u_m ON u.id = u_m.user_id LEFT JOIN " +
                            "musictypes m on u_m.type_id = m.id WHERE m.type = ?;");
            st.setString(1, typeOfMusic.getType());
            rs = super.dbManager.getGo().go(st);
            while (rs.next()){
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
