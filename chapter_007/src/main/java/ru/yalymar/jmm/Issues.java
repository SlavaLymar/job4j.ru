package ru.yalymar.jmm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @since 02.04.2017
 * @version 1
 */
public class Issues {

    private int i = 0;

    /**
     * print i to console
     */
    private void printI(){
        System.out.println(String.format("i: %s", this.i));
    }


    /** main method
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // instance of Issue class
        Issues issues = new Issues();

        // list of threads
        List<Thread> list = new ArrayList<>();

        /**
         * create 3 threads.
         * Each of threads increments i 1000000 times.
         */
        for(int i = 0; i<3; i++){
            list.add(new Thread(){
                @Override
                public void run() {
                    System.out.println(String.format("%s is executing", Thread.currentThread().getName()));
                    for(int i = 0; i<1000000; i++) {
                        issues.i++;
                    }
                }
            });
        }

        // start threads
        for(Thread t: list){
            t.start();
        }

        // wait finish of threads
        for(Thread t: list){
            t.join();
        }

        // print i
        issues.printI();

        System.out.println(String.format("%s thread has finished", Thread.currentThread().getName()));
    }

}
