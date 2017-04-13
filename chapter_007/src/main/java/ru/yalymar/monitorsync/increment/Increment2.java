package ru.yalymar.monitorsync.increment;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author slavalymar
 * @since 04.04.2017
 * @version 1
 */
public class Increment2 {

    /**
     * those what we want to increment
     */
    private int i = 0;

    private Lock lock = new ReentrantLock();

    public int getI() {
        if(lock.tryLock()){
            int result = this.i;
            lock.unlock();
            return result;
        }
        else {
            return this.getI();
        }
    }

    // Class that is thread
    private class IncrementI extends Thread {

        /**
         * increment i
         */
        private void add() {
            if(lock.tryLock()){
                i++;
                lock.unlock();
                return;
            }
            else {
                this.add();
            }
        }

        /**
         * run thread
         */
        @Override
        public void run() {
            for(int i = 0; i<10; i++) {
                System.out.println(this.getName());
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
        Increment2 increment = new Increment2();
        IncrementI thread1 = increment.new IncrementI();
        IncrementI thread2 = increment.new IncrementI();

        // start threads
        thread1.start();
        thread2.start();

        // wait finishing threads
        thread1.join();
        thread2.join();

        // print result
        System.out.println(String.format("Value i: %d", increment.i));

    }
}
