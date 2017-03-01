package ru.yalymar.iterator.convert;

import java.util.Iterator;

public class ConvertIteratorsTest {

    public void whenCreateThreeIteratorsAdnConvertThem(){

        SimpleIterator si1 = new SimpleIterator(new Integer[]{4, 2, 0, 4, 6, 4, 9});
        SimpleIterator si2 = new SimpleIterator(new Integer[]{0, 9, 8, 7, 5});
        SimpleIterator si3 = new SimpleIterator(new Integer[]{1, 3, 5, 6, 7, 0, 9, 8, 4});
        Iterator <Integer> i1 = si1.getIterator();
        Iterator <Integer> i2 = si2.getIterator();
        Iterator <Integer> i3 = si3.getIterator();
        Iterator<Iterator<Integer>> it = new GeneralizedIterator(new Iterator[]{i1, i2, i3});

        ConvertIterators convertIterators = new ConvertIterators();
        convertIterators.convert(it);


    }


}