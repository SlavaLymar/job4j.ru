package ru.yalymar.testtask.model.dao;

import ru.yalymar.testtask.model.TypeOfMusic;
import ru.yalymar.testtask.model.db.DBManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeOfMusicManager extends Manager<TypeOfMusic> {

    public TypeOfMusicManager(DAOFabric daoFabric) {
        super(daoFabric);
    }

    public List<TypeOfMusic> getTypes(int id){
        List<TypeOfMusic> result = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = super.dbManager.getC().prepareStatement(
                            "SELECT mt.id, mt.type FROM musictypes mt JOIN " +
                                    "user_musictype u_m ON mt.id = u_m.type_id where " +
                                    "u_m.user_id = ?;");
            st.setInt(1, id);
            rs = super.dbManager.getGo().go(st);
            while(rs.next()){
                result.add(new TypeOfMusic(rs.getInt("id"),
                        rs.getString("type")));
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
    public int create(TypeOfMusic typeOfMusic) {
        PreparedStatement st = null;
        ResultSet gk = null;
        int id = -1;
        try {
            st = super.dbManager.getC().prepareStatement(
                            "INSERT INTO musictypes (type) values (?)", new String[]{"id"});
            st.setString(1, typeOfMusic.getType());
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

    @Override
    public List<TypeOfMusic> getAll() {
        List<TypeOfMusic> result = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = super.dbManager.getC().prepareStatement(
                            "SELECT * FROM musictypes");
            rs = super.dbManager.getGo().go(st);
            while(rs.next()){
                result.add(new TypeOfMusic(rs.getInt("id"),
                        rs.getString("type")));
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
    public TypeOfMusic getById(int id) {
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = super.dbManager.getC().prepareStatement(
                            "SELECT * FROM musictypes WHERE id = ?");
            st.setInt(1, id);
            rs = super.dbManager.getGo().go(st);
            rs.next();
            return new TypeOfMusic(rs.getInt("id"),
                    rs.getString("type"));
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
    public int edit(int id, TypeOfMusic typeOfMusic) {
        PreparedStatement st = null;
        try {
            st = super.dbManager.getC().prepareStatement(
                            "UPDATE musictypes SET type = ? WHERE id = ?");
            st.setString(1, typeOfMusic.getType());
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

    @Override
    public int remove(int id) {
        PreparedStatement st = null;
        try {
            st = super.dbManager.getC().prepareStatement(
                            "DELETE FROM musictypes WHERE id = ?");
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
