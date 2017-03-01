package ru.yalymar.iterator.convert;

import java.util.Iterator;

/**
 * @author slavalymar
 * @since 01.03.2017
 * @version 1
 */
public class SimpleIterator implements Container{

    /**
     * list of Integers
     */
    private final Integer[] integers;

    public SimpleIterator(final Integer[] integers) {
        this.integers = integers;
    }

    @Override
    public Iterator getIterator() {
        return new IntegerIterator();
    }


    /**
     * @author slavalymar
     * @since 01.03.2017
     * @version 1
     */
    private class IntegerIterator implements Iterator{

        /**
         * index of collection
         */
        int index = 0;

        /** has next
         * @return boolean
         */
        @Override
        public boolean hasNext() {
            if(this.index < integers.length) return true;
            return false;
        }

        /** give a object of collection and increment of index
         * @return Object
         */
        @Override
        public Object next() {
            if(this.hasNext()) return integers[this.index++];
            return -1;
        }
    }

}
