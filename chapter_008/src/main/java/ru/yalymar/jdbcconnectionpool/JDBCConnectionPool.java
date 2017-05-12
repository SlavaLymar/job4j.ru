package ru.yalymar.jdbcconnectionpool;

import ru.yalymar.jdbcconnectionpool.db.DBManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @since 11.05.2017
 * @version 1
 */
public class JDBCConnectionPool {

    /**
     * list of threads
     */
    private List<DBManager> dbManagers = new ArrayList<>();

    private int index = 0;

    /**
     * flag to stop
     */
    private boolean isStopped = false;

    public JDBCConnectionPool() {
        this.dbManagers();
    }

    /**
     * start threads depends of numerous of cpu
     */
    private void dbManagers() {
        for(int i = 0; i<this.getNumbOfCPU(); i++){
            this.dbManagers.add(new DBManager(i+1));
        }
    }

    /** return numbers of cpus
     * @return int
     */
    private int getNumbOfCPU() {
        return Runtime.getRuntime().availableProcessors();
    }

    /** add task to queue
     * @param e
     */
    public void add(Task e){
        e.run(this.getDBManager());
    }

    /**
     * stop JDBCConnectionPool
     */
    public void stop(){
        this.isStopped = true;
        for(DBManager dbManager : this.dbManagers){
            dbManager.disconnectDB();
        }
    }

    /** get dbManager
     * @return DBManager
     */
    public DBManager getDBManager() {
        int size = this.dbManagers.size();
        if(this.index == size) {
            this.index = 0;
        }
        DBManager result = this.dbManagers.get(this.index++);
        boolean flag = false;
        do {
            try {
                if(!result.getC().isClosed()){
                    flag = true;
                }
                else {
                    if(this.index == size) {
                        this.index = 0;
                    }
                    result = this.dbManagers.get(this.index++);
                }
            } catch (SQLException e) {
                DBManager.getLogger().error(e.getMessage(), e);
            }
        }
        while (!flag);
        return result;
    }
}
