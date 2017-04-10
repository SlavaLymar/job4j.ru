package ru.yalymar.waitnotify.producercustomer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author slavalymar
 * @since 10.04.2017
 * @version 1
 */
public class Main {

    /** main method
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        // use queue as store
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        // create store class
        Store<Integer> store = new Store(queue);

        // create Customer`s thread
        Customer t1 = new Customer(store);
        t1.start();

        Thread.sleep(1000);

        // add new obj into store
        new Producer(store, 6).start();

        Thread.sleep(1000);

        // add new obj into store
        new Producer(store, 7).start();

        // finish Customer`s thread
        t1.finishThread();

        // waiting for complete all threads
        do{
            Thread.sleep(1);
        }
        while (Thread.activeCount() > 2);

    }
}
