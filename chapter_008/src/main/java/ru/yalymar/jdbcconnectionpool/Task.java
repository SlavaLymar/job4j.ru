package ru.yalymar.jdbcconnectionpool;

import ru.yalymar.jdbcconnectionpool.db.DBManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author slavalymar
 * @since 11.05.2017
 * @version 1
 */
public class Task {

    /**
     * SQL query
     */
    private String sqlQuery;

    /**
     * if flag is true the Task will return ResultSet to Result class
     */
    private boolean flagGo;

    /**
     * result class
     */
    private Result result;

    /**
     * thread
     */
    private MyThread t;

    public Task(String sqlQuery, boolean flagGo) {
        this.sqlQuery = sqlQuery;
        this.flagGo = flagGo;
        this.result = new Result(this);
    }

    public MyThread getT() {
        return this.t;
    }

    public Result getResult() {
        return this.result;
    }

    /** execute query in depends of flagGO
     * @param t
     */
    public void run(MyThread t){
        this.t = t;
        PreparedStatement st = null;
        try {
            st = t.getDbManager().getC().prepareStatement(this.sqlQuery);
            if(this.flagGo) {
                ResultSet rs = t.getDbManager().getGo().go(st);
                this.result.setRs(rs);
                rs.close();
            }
            else {
                this.result.setResult(t.getDbManager().getGoUpdate().goUpdate(st));
            }
        }
        catch (SQLException e) {
            DBManager.getLogger().error(e.getMessage(), e);
        }
        finally {
            try {
                st.close();
            } catch (SQLException e) {
                DBManager.getLogger().error(e.getMessage(), e);
            }
        }
        System.out.println(String.format("\"%s\" is completed by \"%s\"!", this.sqlQuery, t.getName()));
    }
}
