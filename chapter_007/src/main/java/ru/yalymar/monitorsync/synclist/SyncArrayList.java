package ru.yalymar.monitorsync.synclist;

import java.util.Arrays;
import java.util.Iterator;

public class SyncArrayList<E> {

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

    public SyncArrayList() {
        this.array = new Object[START_CAPACITY];
        this.iteratorList = new SimpleIteratorList<>(this.array);
    }

    public Object[] getArray() {
        return this.array;
    }

    /** add value to array. Extend array if one is full
     * @param e
     */
    public void add(E e) {
        synchronized (this) {
            boolean b = true;
            int count = 0;
            do {
                if (this.array[count] == null) {
                    this.array[count] = e;
                    b = false;
                }
                count++;
                if (count == this.array.length) {
                    this.extendArray();
                }
            }
            while (b);
        }
    }

    /** add obj by index
     * @param i
     * @param e
     */
    public void add(int i, E e) {
        synchronized (this) {
            if (this.array[i] == null) {
                return;
            }
            this.array[i] = e;
        }
    }

    /**
     * extend array
     */
    private void extendArray() {
        synchronized (this) {
            this.array = Arrays.copyOf(this.array, this.array.length * 2);
        }
    }

    /** return value if one exist
     * @param index
     * @return E
     * @throws IndexOutOfBoundsException
     */
    public E get(int index) throws IndexOutOfBoundsException{
        synchronized (this) {
            if (this.array[index] != null) {
                return (E) this.array[index];
            } else throw new NullPointerException();
        }
    }

    /** return Iterator
     * @return Iterator
     */
    public Iterator iterator() {
        return this.iteratorList;
    }


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
}
