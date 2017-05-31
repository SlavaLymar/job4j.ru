package ru.yalymar.testtask.model.dao;

import ru.yalymar.testtask.model.Role;
import ru.yalymar.testtask.model.TypeOfMusic;
import ru.yalymar.testtask.model.db.DBManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeOfMusicManager extends Manager<TypeOfMusic> {

    private DAOFabric daoFabric;

    public TypeOfMusicManager(DAOFabric daoFabric) {
        this.daoFabric = daoFabric;
    }

    @Override
    public int create(TypeOfMusic typeOfMusic) {
        try {
            PreparedStatement st =
                    super.dbManager.getC().prepareStatement(
                            "INSERT INTO musictypes (type) values (?)");
            st.setString(1, typeOfMusic.getType());
            return super.dbManager.getGoUpdate().goUpdate(st);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
            return -1;
        }
    }

    @Override
    public List<TypeOfMusic> getAll() {
        List<TypeOfMusic> result = new ArrayList<>();
        ResultSet rs = null;
        try {
            PreparedStatement st =
                    super.dbManager.getC().prepareStatement(
                            "SELECT * FROM musictypes");
            rs = super.dbManager.getGo().go(st);
            while(rs.next()){
                result.add(new TypeOfMusic(rs.getInt("id"), rs.getString("type")));
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
    public TypeOfMusic getById(int id) {
        ResultSet rs = null;
        try {
            PreparedStatement st =
                    super.dbManager.getC().prepareStatement(
                            "SELECT * FROM musictypes WHERE id = ?");
            st.setInt(1, id);
            rs = super.dbManager.getGo().go(st);
            rs.next();
            return new TypeOfMusic(rs.getInt("id"), rs.getString("type"));
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
    public int edit(int id, TypeOfMusic typeOfMusic) {
        try {
            PreparedStatement st =
                    super.dbManager.getC().prepareStatement(
                            "UPDATE musictypes SET type = ? WHERE id = ?");
            st.setString(1, typeOfMusic.getType());
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
                            "DELETE FROM musictypes WHERE id = ?");
            st.setInt(1, id);
            return super.dbManager.getGoUpdate().goUpdate(st);
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
            return -1;
        }
    }
}
