package ru.yalymar.monitorsync.increment;

/**
 * @author slavalymar
 * @since 04.04.2017
 * @version 1
 */
public class Increment {

    /**
     * those what we want to increment
     */
    private static int i = 0;

    /**
     * monitor of increment class
     */
    private final Object monitor = new Object();

    // Class that is thread
    private class IncrementI extends Thread {

        /**
         * increment i
         */
        private void add() {
            synchronized (monitor) {
                i++;
            }
        }

        /**
         * run thread
         */
        @Override
        public void run() {
            for(int i = 0; i<10000; i++) {
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
        Increment increment = new Increment();
        IncrementI thread1 = increment.new IncrementI();
        IncrementI thread2 = increment.new IncrementI();

        // start threads
        thread1.start();
        thread2.start();

        // wait finishing threads
        thread1.join();
        thread2.join();

        // print result
        System.out.println(String.format("Value i: %d", i));

    }
}
