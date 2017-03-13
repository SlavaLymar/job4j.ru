package ru.yalymar.set.quickaddtoset;

import java.util.Arrays;
import java.util.Iterator;

/**@author slavalymar
 * @since 13.03.2017
 * @version 1
 * @param <E>
 */
public class QuickSimpleHashSet<E> implements Iterator<E>{

    /**
     * array of objects.
     */
    private E[] container;

    /**
     * index is counter of iterator.
     * containerCounter is counter for add objects to container
     */
    private int index = 0, containerCounter = 0;;

    public QuickSimpleHashSet(E[] container) {
        this.container = container;
    }

    public E[] getContainer() {
        return this.container;
    }

    /**add object to container
     * @param e
     * @return boolean
     */
    public boolean add(E e){
        boolean result = true;
        if(this.binarySearch(e, this.container) == 1) {
            result = false;
            return result;
        }
        this.container[this.containerCounter++] = e;
        this.sortedContainer();
        return result;
    }

    private int binarySearch(E e, E[] container) {
        int result = 1;
        int midpoint = (this.containerCounter/2)-1;
        if(container[midpoint].hashCode() < e.hashCode()){
            this.binarySearch(e, Arrays.copyOfRange(container,
                    midpoint+1, container.length-midpoint+1));
        }
        else if(container[midpoint].hashCode() > e.hashCode()){
            this.binarySearch(e, Arrays.copyOfRange(container, 0, midpoint-1));
        }
        else return -1;
        return result;
    }

    /**
     * sorted container depends of hashcode
     */
    public void sortedContainer() {
        for(int i = 0; i<this.container.length; i++){
            for(int j = this.container.length-1; j>i; j--){
                if((this.container[i] != null && this.container[j] != null) &&
                        this.container[i].hashCode() > this.container[j].hashCode()){
                    E tmp = this.container[i];
                    this.container[i] = this.container[j];
                    this.container[j] = tmp;
                }
            }
        }
    }

    /**return true if container has an object
     * @return boolean
     */
    @Override
    public boolean hasNext() {
        return this.index < this.container.length;
    }

    /**return object
     * @return E
     */
    @Override
    public E next() {
        if(this.hasNext()){
            return container[this.index++];
        }
        return null;
    }
}
