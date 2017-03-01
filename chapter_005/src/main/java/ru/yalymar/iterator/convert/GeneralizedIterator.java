package ru.yalymar.iterator.convert;

import java.util.Iterator;
import java.util.List;

public class GeneralizedIterator implements Iterator {

    private final List<Integer> integers;
    private int index = 0;

    public GeneralizedIterator(final List <Integer> integers) {
        this.integers = integers;
    }

    public List<Integer> getIntegers() {
        return integers;
    }

    @Override
    public boolean hasNext() {
        if(this.index < integers.size()) return true;
        return false;
    }

    @Override
    public Object next() {
        if(this.hasNext()) return integers.get(this.index++);
        return -1;
    }

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
