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
    public void whenCreateThreeIteratorsAdnConvertThem(){

        SimpleIterator si1 = new SimpleIterator(new Integer[]{4, 2, 0, 4, 6, 4, 9});
        SimpleIterator si2 = new SimpleIterator(new Integer[]{0, 9, 8, 7, 5});
        SimpleIterator si3 = new SimpleIterator(new Integer[]{1, 3, 5, 6, 7, 0, 9, 8, 4});
        Iterator <Integer> i1 = si1.getIterator();
        Iterator <Integer> i2 = si2.getIterator();
        Iterator <Integer> i3 = si3.getIterator();
        Iterator<Iterator<Integer>> it = new IteratorIterators(new Iterator[]{i1, i2, i3});

        ConvertIterators convertIterators = new ConvertIterators();
        Iterator<Integer> result = convertIterators.convert(it);

        List<Integer> list = Arrays.asList(4, 2, 0, 4, 6, 4, 9, 0, 9, 8, 7, 5, 1, 3, 5, 6, 7, 0, 9, 8, 4);
        Iterator<Integer> expected = new GeneralizedIterator(list);

        assertThat(result, is(expected));
    }


}