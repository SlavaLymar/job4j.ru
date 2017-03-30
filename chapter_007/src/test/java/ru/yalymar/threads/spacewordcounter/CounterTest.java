package ru.yalymar.threads.spacewordcounter;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CounterTest {

    @Test
    public void whenCountStringShouldGetIt() throws InterruptedException {
        Counter counter =
                new Counter(new String("Privet romashki, ty day te dengi... "));

        final int[] count = new int[2];

        // create new Thread
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                count[0] = counter.countSpaces();
            }
        });

        // create new Thred
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                count[1] = counter.countChars();
            }
        });

        // start threads
        t1.start();
        t2.start();

        // wait finish threads t1, t2
        t1.join();
        t2.join();

        assertThat(count[0], is(6));
        assertThat(count[1], is(30));
    }

}