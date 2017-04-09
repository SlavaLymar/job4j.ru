package ru.yalymar.monitorsync.synclist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SyncLinkedList<E> {

    /**
     * size of list
     */
    protected int size = 0;

    /**
     * first value
     */
    protected Node<E> first;

    /**
     * last value
     */
    protected Node<E> last;

    private SimpleIteratorLinkedList iterator = new SimpleIteratorLinkedList();

    public int size() {
        return this.size;
    }

    /** add value to list
     * @param e
     * @return boolean
     */
    public boolean add(E e) {
        synchronized (this) {
            Node<E> l = this.last;
            Node<E> newNode = new Node<>(l, e, null);
            this.last = newNode;
            if (l == null) {
                this.first = newNode;
            } else {
                l.next = newNode;
            }
            this.size++;
            return true;
        }
    }

    /** return value if one exist
     * @param index
     * @return E
     */
    public E get(int index) {
        synchronized (this) {
            if (index >= 0 && index < size) {
                return this.node(index).item;
            }
            return null;
        }
    }

    /** set obj into the index
     * @param index
     * @param e
     * @return E
     */
    public E set(int index, E e) {
        synchronized (this) {
            if (index >= 0 && index < size) {
                return this.node(index).item = e;
            }
            return null;
        }
    }

    /** Returns the (non-null) HasCycle at the specified element index.
     * @param index
     * @return HasCycle<E>
     */
    public Node<E> node(int index) {
        synchronized (this) {
            if (index < (size >> 1)) {
                Node<E> x = first;
                for (int i = 0; i < index; i++)
                    x = x.next;
                return x;
            } else {
                Node<E> x = last;
                for (int i = size - 1; i > index; i--) {
                    x = x.prev;
                }
                return x;
            }
        }
    }

    /** return Iterator
     * @return Iterator
     */
    public Iterator getIterator() {
        return this.iterator;
    }

    /** return new Iterator
     * @return Iterator
     */
    public Iterator getNewIterator() {
        return this.iterator = new SimpleIteratorLinkedList();
    }

    /** class that describe specified element
     * @param <E>
     */
    public class Node<E> {

        public E item;
        public Node<E> next;
        public Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * class that describe Iterator for linked list
     */
    private class SimpleIteratorLinkedList implements Iterator<E>{

        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex = 0;

        /** return true if value is exist
         * @return boolean
         */
        @Override
        public boolean hasNext() {
            return this.nextIndex < size;
        }

        /** return value
         * @return E
         */
        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();

            this.next = node(this.nextIndex);
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        public void reset(){
            this.nextIndex = 0;
        }
    }
}
