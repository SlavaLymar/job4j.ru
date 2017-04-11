package ru.yalymar.waitnotify.threadpool;

import java.util.concurrent.BlockingQueue;

/**
 * @author slavalymar
 * @since 11.04.2017
 * @version 1
 */
public class MyThread extends Thread {

    /**
     * queue of tasks
     */
    private MyBQueue taskQueue;
    private ThreadPool threadPool;

    /**
     * flag to stop
     */
    private boolean isStopped = false;

    /*
    MyThread(MyBQueue taskQueue){
        this.taskQueue = taskQueue;
    }
*/

    MyThread(ThreadPool threadPool){
        this.threadPool = threadPool;
    }

    /**
     * run thread
     */
    @Override
    public void run() {
        while(!this.isStopped()){
            Task task = null;
            try {
                task = (Task) this.threadPool.getTaskQueue().withdraw();
            } catch (InterruptedException e) {
                System.out.println(String.format("%s closed with wait->interrupt method!", this.getName()));
            }
            try {
                task.run(this, task.getName());
                this.sleep(1000);
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
        this.interrupt();
        System.out.println(String.format("%s has been stopped!", this.getName()));
    }


}
