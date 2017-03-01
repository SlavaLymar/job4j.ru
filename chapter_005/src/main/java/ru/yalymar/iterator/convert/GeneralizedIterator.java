package ru.yalymar.iterator.convert;

import java.util.Iterator;

public class GeneralizedIterator implements Iterator {

    private Iterator <Integer>[] gereralizedItearator;

    public GeneralizedIterator(Iterator<Integer>[] gereralizedItearator) {
        this.gereralizedItearator = gereralizedItearator;
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
