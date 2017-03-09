package ru.yalymar.list;

import java.util.Iterator;

/** @author slavalymar
 * @since 09.03.2017
 * @version 1
 * @param <E>
 */
public class SimpleIteratorList<E> implements Iterator {

    /**
     * index of array
     */
    private int index = 0;

    /**
     * an array
     */
    private Object[] array;

    public SimpleIteratorList(Object[] array) {
        this.array = array;
    }

    public int getIndex() {
        return this.index;
    }

    /** return true if value is exist
     * @return boolean
     */
    @Override
    public boolean hasNext() {
        if(this.index < array.length){
            return true;
        }
        return false;
    }

    /** return value
     * @return E
     */
    @Override
    public E next() {
        if(this.hasNext()){
            return (E) array[index++];
        }
        return null;
    }
}
