package ru.yalymar.waitnotify.lock;

/**
 * @author slavalymar
 * @since 13.04.2017
 * @version 1
 */
public class MyLock {

    /**
     * flag to lock
     */
    private boolean isLock = false;

    /**
     * thread that is locking
     */
    private Thread current = null;

    /**
     * count of threads
     */
    private int count = 0;

    /** take lock if it available.
     * If lock is taken thread doesn`t wait
     * @return boolean
     */
    public synchronized boolean tryLock(){
        if(!this.isLock){
            this.count++;
            return this.isLock = true;
        }
        return false;
    }

    /** take lock if it available.
     * If lock is taken thread waits
     * @throws InterruptedException
     */
    public synchronized void lock() throws InterruptedException{
        Thread t = Thread.currentThread();
        while(this.isLock && t != this.current){
            wait();
        }
        this.isLock = true;
        this.count++;
        this.current = t;
    }

    /**
     * unlock
     */
    public synchronized void unlock(){
        this.count--;
        if(this.count == 0) {
            this.isLock = false;
            notify();
        }
    }
}
