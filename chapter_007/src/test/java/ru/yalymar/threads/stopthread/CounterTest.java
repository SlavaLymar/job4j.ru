package ru.yalymar.threads.stopthread;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CounterTest {

    @Test
    public void whenCountStringShouldGetIt() throws InterruptedException {

        String string = "Privet romashki, ty day te dengi... ";
        System.out.println(String.format("%s%s%s",
                "This program will calculate spaces and chars of string: \"", string, "\"."));
        Counter counter =
                new Counter(string);

        final int[] count = new int[2];

        // create new Thread
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    count[0] = counter.countSpaces();
                }
            }
        });

        // create new Thread
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!Thread.currentThread().isInterrupted()) {
                    count[1] = counter.countChars();
                }
            }
        });

        // start threads
        t1.start();
        t2.start();

        /**
         * wait finish threads t1, t2
         */
        t1.join();
        t2.join();

        System.out.println("Finish program in main thread.");

        assertThat(count[0], is(6));
        assertThat(count[1], is(30));
    }
}