package ru.yalymar.set.linkedlistsimpleset;

import ru.yalymar.list.simplelinkedlist.SimpleLinkedList;
import java.util.Iterator;

/**@author slavalymar
 * @since 13.03.2017
 * @version 1
 * @param <E>
 */
public class SimpleLinkedSet<E> extends SimpleLinkedList<E> {

    /**
     * container of objects.
     */
    private SimpleLinkedList<E> container;

    /**
     * iterator of set collection.
     */
    private SimpleLinkedSetIterator setIterator;

    public SimpleLinkedSet(SimpleLinkedList<E> container) {
        this.container = container;
    }

    public SimpleLinkedList<E> getContainer() {
        return this.container;
    }

    /**add object to container
     * @param e
     * @return boolean
     */
    @Override
    public boolean add(E e) {
        boolean result = true;
        if(this.isDuplicate(e)){
            result = false;
            return result;
        }
        this.container.add(e);
        return result;
    }

    /**checked if an object is a duplicate
     * @param e
     * @return boolean
     */
    private boolean isDuplicate(E e){
        boolean result = false;
        this.setIterator = new SimpleLinkedSetIterator(this.container.getNewIterator());
        while(this.setIterator.hasNext()){
            if(this.setIterator.next().equals(e)){
                result = true;
                return result;
            }
        }
        return result;
    }

    /**
     * Class of set collection`s Iterator
     */
    private class SimpleLinkedSetIterator implements Iterator<E>{

        /**
         * instance of set collection`s iterator
         */
        private Iterator<E> simpleLinkedSetIterator;

        public SimpleLinkedSetIterator(Iterator<E> simpleLinkedSetIterator) {
            this.simpleLinkedSetIterator = simpleLinkedSetIterator;
        }

        /** return true if value is exist
         * @return boolean
         */
        @Override
        public boolean hasNext() {
            return simpleLinkedSetIterator.hasNext();
        }

        /** return object
         * @return E
         */
        @Override
        public E next() {
            return simpleLinkedSetIterator.next();
        }
    }
}
