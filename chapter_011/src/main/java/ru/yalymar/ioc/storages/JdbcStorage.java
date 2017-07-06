package ru.yalymar.ioc.storages;

import org.springframework.stereotype.Component;
import ru.yalymar.ioc.models.Role;
import ru.yalymar.ioc.models.User;
import ru.yalymar.ioc.storages.db.DBManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class JdbcStorage implements Storage {

    private final DBManager dbManager = new DBManager();

    @Override
    public int add(User user) {
        int id = -1;
        PreparedStatement st = null;
        ResultSet gk = null;
            try {
                st = this.dbManager.getC().prepareStatement(
                        "INSERT INTO users (login, password, role_id)" +
                                "VALUES (?, ?, (SELECT r.id FROM roles r WHERE r.role = ?));",
                        new String[]{"id"});
                st.setString(1, user.getLogin());
                st.setString(2, user.getPassword());
                st.setString(3, user.getRole().getRole());
                this.dbManager.getGoUpdate().goUpdate(st);

                gk = st.getGeneratedKeys();
                if(gk.next()){
                    id = gk.getInt("id");
                }
                return id;
            }
            catch (SQLException e) {
                DBManager.logger.error(e.getMessage(), e);
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
                    DBManager.logger.error(e.getMessage(), e);
                }
            }
        return -1;
    }

    @Override
    public User get(User user) {
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = this.dbManager.getC().prepareStatement(
                    "SELECT u.id, u.login, u.password, r.role FROM users JOIN " +
                            "roles r on u.role_id = r.id WHERE id = ?");
            st.setInt(1, Integer.parseInt(user.getId()));
            rs = this.dbManager.getGo().go(st);
            rs.next();
            return new User(rs.getString("login"),
                    rs.getString("password"),
                    new Role(rs.getString("role")));
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
    public boolean update(User user, User newUser) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }
}
