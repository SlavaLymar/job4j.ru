package ru.yalymar.list.simplequeue;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleQueueTest {

    @Test
    public void whenPushCoupleValueToQueueThenGetLastWithPeek(){
        SimpleQueue<Integer> simpleQueue = new SimpleQueue<>();
        simpleQueue.offer(1);
        simpleQueue.offer(2);
        simpleQueue.offer(3);

        int result = simpleQueue.peek();
        assertThat(result, is(1));
        assertThat(result, is(1));
    }

    @Test
    public void whenPushCoupleValueToQueueThenGetLastWithPoll(){
        SimpleQueue<Integer> simpleQueue = new SimpleQueue<>();
        simpleQueue.offer(1);
        simpleQueue.offer(2);
        simpleQueue.offer(3);

        int result = simpleQueue.poll();
        assertThat(result, is(1));
        int result1 = simpleQueue.poll();
        assertThat(result1, is(2));
        int result2 = simpleQueue.poll();
        assertThat(result2, is(3));
    }

}