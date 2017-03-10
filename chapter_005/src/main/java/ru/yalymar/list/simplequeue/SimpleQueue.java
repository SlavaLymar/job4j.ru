package ru.yalymar.list.simplequeue;

import ru.yalymar.list.simplelinkedlist.SimpleLinkedList;

/**@author slavalymar
 * @since 10.03.2017
 * @version 1
 * @param <E>
 */
public class SimpleQueue<E> extends SimpleLinkedList<E> {

    /** Inserts the specified element into this queue
     * @param e
     * @return boolean
     */
    public boolean offer(E e) {
        if(super.add(e)) return true;
        return false;
    }

    /**Retrieves and removes the head of this queue,
     * or returns null if this queue is empty.
     * @return E
     */
    public E poll() {
        Node<E> f = first;
        E result = unlinkFirst(f);
        return result == null ? null : result;
    }

    /** Retrieves and removes the head of this queue.
     * @param f
     * @return E
     */
    protected E unlinkFirst(Node<E> f) {
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return element;
    }

    /**Retrieves, but does not remove, the head of this queue,
     * or returns null if this queue is empty.
     * @return E
     */
    public E peek() {
        return first.item == null ? null : first.item;
    }


}
