package ru.yalymar.waitnotify.threadpool;

import java.util.concurrent.BlockingQueue;

public class MyThread extends Thread {

    private BlockingQueue taskQueue;
    private boolean isStopped = false;

    MyThread(BlockingQueue taskQueue){
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while(!this.isStopped()){
            this.taskQueue.poll();
        }
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    synchronized void doStop(){
        this.isStopped = true;
        this.interrupt();
    }


}
