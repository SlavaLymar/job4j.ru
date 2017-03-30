package ru.yalymar.threads.spacewordcounter;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CounterTest {

    @Test
    public void whenCountStringShouldGetIt(){
        Counter counter =
                new Counter(new String("Privet romashki, ty day te dengi... "));

        final int[] count = new int[2];

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                count[0] = counter.countSpaces();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                count[1] = counter.countChars();
            }
        });

        t1.start();
        t2.start();

        assertThat(count[0], is(6));
        assertThat(count[1], is(30));
    }

}