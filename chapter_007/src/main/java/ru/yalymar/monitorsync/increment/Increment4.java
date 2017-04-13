package ru.yalymar.monitorsync.increment;

/**
 * @author slavalymar
 * @since 04.04.2017
 * @version 1
 */
public class Increment4 {

    /**
     * those what we want to increment
     */
    private int i = 0;

    public synchronized int getI() {
        return this.i;
    }

    // Class that is thread
    private class IncrementI extends Thread {

        int tmp;

        IncrementI(int initTmp){
            this.tmp = initTmp;
        }

        /**
         * increment tmp
         */
        private void add() {
            System.out.println(this.getName());
            this.tmp++;
        }

        /**
         * run thread
         */
        @Override
        public void run() {
            for(int i = 0; i<10000; i++) {
                this.add();
            }

            // working :)
            i = i + this.tmp;
        }
    }

    /** main method
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // initialized
        Increment4 increment = new Increment4();
        IncrementI thread1 = increment.new IncrementI(increment.getI());
        IncrementI thread2 = increment.new IncrementI(increment.getI());

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
