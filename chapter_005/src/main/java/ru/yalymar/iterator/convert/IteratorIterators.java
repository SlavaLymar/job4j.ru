package ru.yalymar.iterator.convert;

import java.util.Iterator;

public class IteratorIterators implements Iterator {

    private Iterator <Integer>[] gereralizedItearator;
    private int index = 0;

    public IteratorIterators(Iterator<Integer>[] gereralizedItearator) {
        this.gereralizedItearator = gereralizedItearator;
    }

    @Override
    public boolean hasNext() {
        if(this.index < this.gereralizedItearator.length) return true;
        return false;
    }

    @Override
    public Iterator<Integer> next() {
        if(this.hasNext()) return this.gereralizedItearator[this.index++];
        return null;
    }
}
