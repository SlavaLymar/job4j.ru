package ru.yalymar.waitnotify.producercustomer;

/**
 * @author slavalymar
 * @since 10.04.2017
 * @version 1
 */
public class Producer<E> extends Thread{

    /**
     * instance of store class
     */
    private Store store;

    /**
     * current thread`s name
     */
    private final String THREADNAME = "Producer-Thread";

    /**
     * obj for addiction
     */
    private E e;

    public Producer(Store store, E e){
        this.store = store;
        this.e = e;
        this.setName(THREADNAME);
    }

    @Override
    public void run() {
        System.out.println(String.format("Start %s!", this.getName()));
        this.store.add(e);
        System.out.println(String.format("%s has finished!", this.getName()));
    }


}
