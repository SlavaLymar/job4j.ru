package ru.yalymar.jdbcconnectionpool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @since 11.05.2017
 * @version 1
 */
public class JDBCConnectionPool {

    /**
     * queue of tasks
     */
    private MyBQueue<Task> taskQueue;

    /**
     * list of threads
     */
    private List<MyThread> threads = new ArrayList<>();

    /**
     * flag to stop
     */
    private boolean isStopped = false;

    public JDBCConnectionPool() {
        this.taskQueue = new MyBQueue<>();
        this.startThreads();
    }

    /**
     * start threads depends of numerous of cpu
     */
    private void startThreads() {
        for(int i = 0; i<this.getNumbOfCPU(); i++){
            this.threads.add(new MyThread(this));
        }
        for(MyThread t : this.threads){
            t.start();
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
    public synchronized void add(Task e) throws InterruptedException {
        if(this.isStopped) throw new IllegalStateException("JDBCConnectionPool is stopped!");

        this.taskQueue.addiction(e);
    }

    /**
     * stop JDBCConnectionPool
     */
    public synchronized void stop(){
        this.isStopped = true;
        for(MyThread t : this.threads){
            t.doStop();
        }
    }

    public MyBQueue<Task> getTaskQueue() {
        return this.taskQueue;
    }
}
