package ru.counter;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @autor slavalymar
 * @since 02.01.2017
 * @version 1
 */
public class CounterTest {
    @Test
    public void addTest() {
        Counter counter = new Counter();
        int result = counter.add(0, 5);
        assertThat(result, is(6));
    }

}