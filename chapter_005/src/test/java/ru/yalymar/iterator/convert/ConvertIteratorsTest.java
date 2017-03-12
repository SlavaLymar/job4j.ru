package ru.yalymar.iterator.convert;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConvertIteratorsTest {

    @Test
    public void whenCreateThreeIteratorsAndConvertThem(){
        List <Iterator<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>(Arrays.asList(new Integer[]{4, 5, 7})).iterator());
        list.add(new ArrayList<>(Arrays.asList(new Integer[]{2, 6, 8, 10})).iterator());
        list.add(new ArrayList<>(Arrays.asList(new Integer[]{1})).iterator());

        ConvertIterators ci = new ConvertIterators();
        Iterator <Integer> resultIt = ci.convert(list.iterator());

        Integer[] expected = new Integer[]{4, 5, 7, 2, 6, 8, 10, 1};
        Integer[] result = new Integer[8];

        int index = 0;
        while(resultIt.hasNext()){
            result[index++] = resultIt.next();
        }

        assertThat(result, is(expected));
    }

    @Test
    public void whenCreateIteratorsAndNullAndConvertThem(){
        List <Iterator<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>(Arrays.asList(new Integer[]{4, 5, 7})).iterator());
        list.add(new ArrayList<>(Arrays.asList(new Integer[]{})).iterator());
        list.add(new ArrayList<>(Arrays.asList(new Integer[]{1})).iterator());

        ConvertIterators ci = new ConvertIterators();
        Iterator <Integer> resultIt = ci.convert(list.iterator());

        Integer[] expected = new Integer[]{4, 5, 7, 1};
        Integer[] result = new Integer[4];

        int index = 0;
        while(resultIt.hasNext()){
            result[index++] = resultIt.next();
        }

        assertThat(result, is(expected));
    }

}