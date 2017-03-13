package ru.yalymar.set.arraylistsimpleset;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SimpleHashSetTest {

    @Test
    public void whenAddToSetDuplicateThenItGetNull(){
        Integer[] array = new Integer[10];
        SimpleHashSet<Integer> set = new SimpleHashSet<>(array);

        set.add(1);
        set.add(2);
        set.add(1);

        assertNull(set.getContainer()[2]);
        assertThat(set.getContainer()[1], is(2));
    }

    @Test
    public void whenAddToSetDuplicateThenGetValue(){
        Integer[] array = new Integer[10];
        SimpleHashSet<Integer> set = new SimpleHashSet<>(array);

        set.add(1);
        set.add(2);
        set.add(1);

        assertThat(set.getContainer()[1], is(2));
    }

    @Test
    public void whenAddToSetValuesThenGetValueWithIterator(){
        Integer[] array = new Integer[10];
        SimpleHashSet<Integer> set = new SimpleHashSet<>(array);

        set.add(1);
        set.add(2);
        set.add(1);

        Integer[] resultArray = new Integer[10];
        int index = 0;
        while(set.hasNext()){
            resultArray[index++] = set.next();
        }

        assertEquals(set.getContainer(), resultArray);
    }

    @Test
    public void whenAddToSetValuesThenCompareHashCodes(){
        Integer[] array = new Integer[10];
        SimpleHashSet<Integer> set = new SimpleHashSet<>(array);

        set.add(1);
        set.add(2);
        set.add(-1);
        set.add(14);
        set.add(3);

        assertThat(set.getContainer()[0], is(-1));
    }
}