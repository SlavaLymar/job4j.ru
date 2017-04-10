package ru.yalymar.waitnotify.producercustomer;

/**
 * @author slavalymar
 * @since 10.04.2017
 * @version 1
 */
public class Customer extends Thread{

    /**
     * instance of store class
     */
    private Store store;

    /**
     * current thread`s name
     */
    private final String THREADNAME = "Customer-Thread";

    /**
     * flag to finish the thread
     */
    private boolean exit;

    public Customer(Store store){
        this.store = store;
        this.setName(THREADNAME);
        this.exit = true;
    }

    @Override
    public void run() {
        System.out.println(String.format("Start %s!", this.getName()));
        synchronized (this.store.getQueue()) {
            while (this.exit) {
                if (this.store.getQueue().isEmpty()) {
                    try {
                        this.store.getQueue().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.store.get();
            }
        }
    }

    /**
     * finish the thread
     */
    public void finishThread() {
        synchronized (this.store.getQueue()) {
            if (this.getState() == State.WAITING) {
                this.store.getQueue().notify();
                this.exit = false;
            }
            if (this.getState() == State.RUNNABLE) {
                this.exit = false;
            }
        }
        System.out.println(String.format("%s has been finished!", this.getName()));
    }
}
