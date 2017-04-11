package ru.yalymar.waitnotify.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

    private BlockingQueue taskQueue;
    private List<MyThread> threads = new ArrayList<>();
    private boolean isStopped = false;

    public ThreadPool() {
        this.taskQueue = new LinkedBlockingQueue();
        this.startThreads();
    }

    private void startThreads() {
        for(int i = 0; i<this.getNumbOfCPU(); i++){
            this.threads.add(new MyThread(this.taskQueue));
        }
        for(MyThread t : this.threads){
            t.start();
        }
    }

    private int getNumbOfCPU() {
        return Runtime.getRuntime().availableProcessors();
    }

    public synchronized void add(Task task){
        if(this.isStopped) throw new IllegalStateException("ThreadPool is stopped!");

        this.taskQueue.offer(task);
    }

    public synchronized void stop(){
        this.isStopped = true;
        for(MyThread t : this.threads){
            t.doStop();
        }
    }
}
