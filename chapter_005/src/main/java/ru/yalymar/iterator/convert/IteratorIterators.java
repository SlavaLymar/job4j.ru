package ru.yalymar.iterator.convert;

import java.util.Iterator;

/**
 * @author slavalymar
 * @since 01.03.2017
 * @version 1
 */
public class IteratorIterators implements Iterator {

    /**
     * list of Iterators
     */
    private Iterator <Integer>[] gereralizedItearator;

    /**
     * index of collection
     */
    private int index = 0;

    public IteratorIterators(Iterator<Integer>[] gereralizedItearator) {
        this.gereralizedItearator = gereralizedItearator;
    }

    /** has next
     * @return boolean
     */
    @Override
    public boolean hasNext() {
        if(this.index < this.gereralizedItearator.length) return true;
        return false;
    }

    /** give a iterator of collection and increment of index
     * @return Iterator
     */
    @Override
    public Iterator<Integer> next() {
        if(this.hasNext()) return this.gereralizedItearator[this.index++];
        return null;
    }
}
