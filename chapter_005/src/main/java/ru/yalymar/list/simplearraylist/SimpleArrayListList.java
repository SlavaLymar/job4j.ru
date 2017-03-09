package ru.yalymar.list.simplearraylist;

import java.util.Arrays;
import java.util.Iterator;

/** @author slavalymar
 * @since 09.03.2017
 * @version 1
 * @param <E>
 */
public class SimpleArrayListList<E> implements SimpleContainerList<E> {

    /**
     * default capacity of array
     */
    private final int START_CAPACITY = 10;

    /**
     * an array
     */
    private Object[] array;

    /**
     * iterator
     */
    private SimpleIteratorList<E> iteratorList;

    public SimpleArrayListList() {
        this.array = new Object[START_CAPACITY];
        this.iteratorList = new SimpleIteratorList<>(this.array);
    }

    public Object[] getArray() {
        return this.array;
    }

    /** add value to array. Extend array if one is full
     * @param e
     */
    @Override
    public void add(E e) {
        boolean b = true;
        int count = 0;
        do{
            if(this.array[count] == null){
                this.array[count] = e;
                b = false;
            }
            count++;
            if(count == this.array.length){
                this.extendArray();
            }
        }
        while(b);
    }

    /**
     * extend array
     */
    private void extendArray() {
        this.array = Arrays.copyOf(this.array, this.array.length*2);
    }

    /** return value if one exist
     * @param index
     * @return E
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException{
        if(this.array[index] != null) {
            return (E) this.array[index];
        }
        else throw new NullPointerException();
    }

    /** return Iterator
     * @return Iterator
     */
    @Override
    public Iterator iterator() {
        return this.iteratorList;
    }
}
