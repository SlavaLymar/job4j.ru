package ru.yalymar.jdbcconnectionpool;

import ru.yalymar.jdbcconnectionpool.db.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author slavalymar
 * @since 11.05.2017
 * @version 1
 */
public class Result {

    private ResultSet rs;
    private Integer result;
    private Task task;

    public Result(Task task) {
        this.task = task;
    }

    /** get ResultSet and close it
     * @return ResultSet
     */
    public ResultSet getRs() {
        try{
            return this.rs;
        }
        finally {
            this.closeRS();
        }
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public Integer getResult() {
        return this.result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public void closeRS(){
        if(this.rs != null){
            try {
                this.rs.close();
            }
            catch (SQLException e) {
                DBManager.getLogger().error(e.getMessage(), e);
            }
        }
    }
}
