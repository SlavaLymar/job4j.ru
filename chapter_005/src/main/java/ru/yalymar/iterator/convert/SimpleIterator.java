package ru.yalymar.iterator.convert;

import java.util.Iterator;

public class SimpleIterator implements Container{

    private final Integer[] integers;

    public SimpleIterator(final Integer[] integers) {
        this.integers = integers;
    }

    @Override
    public Iterator getIterator() {
        return new IntegerIterator();
    }

    private class IntegerIterator implements Iterator{

        int index = 0;

        @Override
        public boolean hasNext() {
            if(this.index < integers.length) return true;
            return false;
        }

        @Override
        public Object next() {
            if(this.hasNext()) return integers[this.index++];
            return -1;
        }
    }

}
