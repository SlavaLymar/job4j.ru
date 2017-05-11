package ru.yalymar.jdbcconnectionpool;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Result {

    private ResultSet rs;
    private Integer result;
    private Task task;

    public Result(Task task) {
        this.task = task;
    }

    public ResultSet getRs() {
        return this.rs;
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

            }
        }
    }
}
