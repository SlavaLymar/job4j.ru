package ru.yalymar.waitnotify.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author slavalymar
 * @since 11.04.2017
 * @version 1
 */
public class ThreadPool {

    /**
     * queue of tasks
     */
    private BlockingQueue taskQueue;

    /**
     * list of threads
     */
    private List<MyThread> threads = new ArrayList<>();

    /**
     * flag to stop
     */
    private boolean isStopped = false;

    public ThreadPool() {
        this.taskQueue = new LinkedBlockingQueue();
        this.startThreads();
    }

    /**
     * start threads depends of numerous of cpu
     */
    private void startThreads() {
        for(int i = 0; i<this.getNumbOfCPU(); i++){
            this.threads.add(new MyThread(this.taskQueue));
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
     * @param task
     */
    public synchronized void add(Task task){
        if(this.isStopped) throw new IllegalStateException("ThreadPool is stopped!");

        this.taskQueue.add(task);
    }

    /**
     * stop ThreadPool
     */
    public synchronized void stop(){
        this.isStopped = true;
        for(MyThread t : this.threads){
            t.doStop();
        }
    }
}
