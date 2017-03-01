package ru.yalymar.iterator.convert;

import java.util.Iterator;

public class ConvertIterators implements Convert, Iterator{

    private Iterator<Integer> generalizedIterator;
    private Iterator<Iterator<Integer>> it;

    @Override
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        this.it = it;

        //TODO do convert 01/03/2017
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }
}
