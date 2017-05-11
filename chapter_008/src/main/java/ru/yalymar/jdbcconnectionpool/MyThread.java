package ru.yalymar.jdbcconnectionpool;

import ru.yalymar.jdbcconnectionpool.db.DBManager;

/**
 * @author slavalymar
 * @since 11.05.2017
 * @version 1
 */
public class MyThread extends Thread {

    private JDBCConnectionPool jDBCConnectionPool;
    private DBManager dbManager;
    /**
     * flag to stop
     */
    private boolean isStopped = false;

    MyThread(JDBCConnectionPool jDBCConnectionPool){
        this.jDBCConnectionPool = jDBCConnectionPool;
        this.dbManager = new DBManager();
    }

    public JDBCConnectionPool getjDBCConnectionPool() {
        return this.jDBCConnectionPool;
    }

    public DBManager getDbManager() {
        return this.dbManager;
    }

    /**
     * run thread
     */
    @Override
    public void run() {
        while(!this.isStopped()){
            Task task = null;
            try {
                task = this.jDBCConnectionPool.getTaskQueue().withdraw();
            } catch (InterruptedException e) {
                System.out.println(String.format("%s closed with wait->interrupt method!", this.getName()));
            }
            try {
                task.run(this);
                this.sleep(500);
            }
            catch(Exception e){
                //System.out.println(String.format("%s is waiting for a task!", this.getName()));
            }
        }
    }

    /** return flag to stop thread
     * @return boolean
     */
    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    /**
     * stop thread
     */
    synchronized void doStop(){
        this.isStopped = true;
        this.dbManager.disconnectDB();
        this.interrupt();
        System.out.println(String.format("%s has been stopped!", this.getName()));
    }


}
