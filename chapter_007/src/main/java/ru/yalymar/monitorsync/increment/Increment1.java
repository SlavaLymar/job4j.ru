package ru.yalymar.monitorsync.increment;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author slavalymar
 * @since 04.04.2017
 * @version 1
 */
public class Increment1 {

    /**
     * those what we want to increment
     */
    private AtomicInteger i = new AtomicInteger(0);

    public AtomicInteger getI() {
        return this.i;
    }

    // Class that is thread
    private class IncrementI extends Thread {

        /**
         * increment i
         */
        private void add() {
            System.out.println(this.getName());
            i.incrementAndGet();
        }

        /**
         * run thread
         */
        @Override
        public void run() {
            for(int i = 0; i<10; i++) {
                this.add();
            }
        }
    }

    /** main method
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // initialized
        Increment1 increment = new Increment1();
        IncrementI thread1 = increment.new IncrementI();
        IncrementI thread2 = increment.new IncrementI();

        // start threads
        thread1.start();
        thread2.start();

        // wait finishing threads
        thread1.join();
        thread2.join();

        // print result
        System.out.println(String.format("Value i: %s", increment.getI().toString()));

    }
}
