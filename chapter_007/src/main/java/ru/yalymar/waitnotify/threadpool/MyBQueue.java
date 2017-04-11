package ru.yalymar.waitnotify.threadpool;

import java.util.LinkedList;
import java.util.List;

public class MyBQueue<E> {

    private List<E> queue = new LinkedList<>();
    public int limit = 10;

    public MyBQueue(int limit) {
        this.limit = limit;
    }

    public MyBQueue() {
    }

    public synchronized void addiction(E e) throws InterruptedException{
        while(this.queue.size() == this.limit){
            wait();
        }
        if(this.queue.size() == 0){
            notifyAll();
        }
        this.queue.add(e);
    }

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
