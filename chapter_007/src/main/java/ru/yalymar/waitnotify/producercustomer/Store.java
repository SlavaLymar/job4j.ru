package ru.yalymar.waitnotify.producercustomer;

import java.util.Queue;

/**
 * @author slavalymar
 * @since 10.04.2017
 * @version 1
 */
public class Store<E>{

    /**
     * store
     */
    private Queue<E> queue;

    /**
     * current thread`s name
     */
    private final String THREADNAME = "Main-Thread";

    public Store(Queue queue) {
        this.queue = queue;
    }

    /**
     * @return queue
     */
    public Queue<E> getQueue() {
        return this.queue;
    }

    public void add(E e){
        synchronized (queue){
            queue.offer(e);
            queue.notify();
        }
    }

    /** get obj
     * @return E
     */
    public E get(){
        synchronized (queue) {
            if(this.queue.isEmpty()){
                return null;
            }

            E result = null;
            System.out.println(String.format("%s has taken %s",
                    Thread.currentThread().getName(), queue.peek().toString()));
            result = queue.poll();
            System.out.println(String.format("It has %d obj in store.", queue.size()));
            return result;
        }
    }

}
