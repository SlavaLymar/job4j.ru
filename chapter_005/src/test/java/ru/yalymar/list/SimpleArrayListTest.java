package ru.yalymar.list;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleArrayListTest {

    @Test
    public void whenAddValueToDynamicArray(){
        SimpleArrayList<Integer> simpleArrayList = new SimpleArrayList<>();

        simpleArrayList.add(1);

        assertThat(simpleArrayList.getArray()[0], is(1));
    }

    @Test
    public void whenGetValueFromDynamicArray(){
        SimpleArrayList<Integer> simpleArrayList = new SimpleArrayList<>();

        simpleArrayList.add(1);
        int result = simpleArrayList.get(0);

        assertThat(result, is(1));
    }

    @Test(expected = NullPointerException.class)
    public void whenGetValueThenThrowNullPointerException(){
        SimpleArrayList<Integer> simpleArrayList = new SimpleArrayList<>();

        simpleArrayList.get(0);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenGetValueThenThrowOutOfBoundsException(){
        SimpleArrayList<Integer> simpleArrayList = new SimpleArrayList<>();

        simpleArrayList.get(11);
    }

    @Test
    public void whenExtendedSizeOfArray(){
        SimpleArrayList<Integer> simpleArrayList = new SimpleArrayList<>();

        simpleArrayList.add(1);
        simpleArrayList.add(1);
        simpleArrayList.add(1);
        simpleArrayList.add(1);
        simpleArrayList.add(1);
        simpleArrayList.add(1);
        simpleArrayList.add(1);
        simpleArrayList.add(1);
        simpleArrayList.add(1);
        simpleArrayList.add(1);
        simpleArrayList.add(2);

        assertThat(simpleArrayList.getArray().length, is(20));
        assertThat(simpleArrayList.getArray()[10], is(2));
        assertThat(simpleArrayList.getArray()[1], is(1));
    }

}