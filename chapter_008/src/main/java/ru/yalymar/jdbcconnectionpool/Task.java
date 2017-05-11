package ru.yalymar.jdbcconnectionpool;

import ru.yalymar.jdbcconnectionpool.db.DBManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Task {

    private String sqlQuery;
    private boolean flagGo;
    private Result result;
    private MyThread t;

    public Task(String sqlQuery, boolean flagGo) {
        this.sqlQuery = sqlQuery;
        this.flagGo = flagGo;
        this.result = new Result(this);
    }

    public String getSqlQuery() {
        return this.sqlQuery;
    }

    public Result getResult() {
        return this.result;
    }

    public MyThread getT() {
        return this.t;
    }

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
