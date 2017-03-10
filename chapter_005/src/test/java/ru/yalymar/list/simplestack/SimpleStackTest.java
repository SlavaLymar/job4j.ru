package ru.yalymar.list.simplestack;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleStackTest {

    @Test
    public void whenPushCoupleValueToStackThenGetLastWithPeek(){
        SimpleStack<Integer> simpleStack = new SimpleStack<>();
        simpleStack.push(1);
        simpleStack.push(2);
        simpleStack.push(3);

        int result = simpleStack.peek();
        assertThat(result, is(3));
        assertThat(result, is(3));
    }


    @Test
    public void whenPushCoupleValueToStackThenGetLastWithPop(){
        SimpleStack<Integer> simpleStack = new SimpleStack<>();
        simpleStack.push(1);
        simpleStack.push(2);
        simpleStack.push(3);

        int result = simpleStack.pop();
        assertThat(result, is(3));
        int result1 = simpleStack.pop();
        assertThat(result1, is(2));
        int result2 = simpleStack.pop();
        assertThat(result2, is(1));
    }

}