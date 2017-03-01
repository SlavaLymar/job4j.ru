package ru.yalymar.iterator.convert;

import java.util.Iterator;
import java.util.List;

/**
 * @author slavalymar
 * @since 01.03.2017
 * @version 1
 */
public class GeneralizedIterator implements Iterator {

    /**
     * list of Integers
     */
    private final List<Integer> integers;

    /**
     * index of collection
     */
    private int index = 0;

    public GeneralizedIterator(final List <Integer> integers) {
        this.integers = integers;
    }

    public List<Integer> getIntegers() {
        return integers;
    }

    /** has next
     * @return boolean
     */
    @Override
    public boolean hasNext() {
        if(this.index < integers.size()) return true;
        return false;
    }

    /** give a object of collection and increment of index
     * @return Object
     */
    @Override
    public Object next() {
        if(this.hasNext()) return integers.get(this.index++);
        return -1;
    }

    /** equals Iterators
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        Iterator<Integer> i = (Iterator<Integer>) obj;

        while(this.hasNext()) {
            if (i.hasNext()) {
                if (i.next().equals(this.next())) {
                    result = true;
                    if(this.index == this.getIntegers().size()-1) return result;
                }
                else result = false;
                return result;
            }
        }
        return result;
    }


}
