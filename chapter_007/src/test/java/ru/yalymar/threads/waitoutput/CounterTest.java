package ru.yalymar.threads.waitoutput;

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
                count[0] = counter.countSpaces();
            }
        });

        // create new Thread
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                count[1] = counter.countChars();
            }
        });

        // start threads
        t1.start();
        t2.start();

        /**
         * wait finish threads t1, t2 in 1 second.
         * After that set flag of interrupt = true
         */
        t1.join(1000);
        t1.interrupt();
        t2.join();
        t1.join();

        System.out.println("Finish program in main thread.");

        assertThat(count[0], is(0));
        assertThat(count[1], is(30));
    }
}