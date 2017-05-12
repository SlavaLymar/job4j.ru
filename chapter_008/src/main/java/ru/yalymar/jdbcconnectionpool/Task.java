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

    public Task(String sqlQuery, boolean flagGo) {
        this.sqlQuery = sqlQuery;
        this.flagGo = flagGo;
        this.result = new Result(this);
    }

    public Result getResult() {
        return this.result;
    }

    /**
     * execute query in depends of flagGO
     */
    public void run(DBManager dbManager){
        PreparedStatement st = null;
        try {
            st = dbManager.getC().prepareStatement(this.sqlQuery);
            if(this.flagGo) {
                ResultSet rs = dbManager.getGo().go(st);
                this.result.setRs(rs);
                rs.close();
            }
            else {
                this.result.setResult(dbManager.getGoUpdate().goUpdate(st));
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
        System.out.println(String.format("\"%s\" is completed by DBManager #\"%d\"!", this.sqlQuery, dbManager.getI()));
    }
}
