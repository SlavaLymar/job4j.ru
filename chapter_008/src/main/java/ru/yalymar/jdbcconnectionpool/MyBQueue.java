package ru.yalymar.jdbcconnectionpool;

import java.util.LinkedList;
import java.util.List;

/**
 * @author slavalymar
 * @since 11.04.2017
 * @version 1
 */
public class MyBQueue<E> {

    /**
     * queue of tasks
     */
    private List<E> queue = new LinkedList<>();

    /**
     * max of objects
     */
    public int limit = 10;

    public MyBQueue(int limit) {
        this.limit = limit;
    }

    public MyBQueue() {
    }

    /** add obj into queue
     * @param e
     * @throws InterruptedException
     */
    public synchronized void addiction(E e) throws InterruptedException{
        while(this.queue.size() == this.limit){
            wait();
        }
        if(this.queue.size() == 0){
            notifyAll();
        }
        this.queue.add(e);
    }

    /** get object from queue and delete it
     * @return E
     * @throws InterruptedException
     */
    public synchronized E withdraw() throws InterruptedException {
        while(this.queue.size() == 0){
            wait();
        }
        if(this.queue.size() == this.limit){
            notifyAll();
        }
        return this.queue.remove(0);
    }
}
