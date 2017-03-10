package ru.yalymar.list.simplestack;

import ru.yalymar.list.simplelinkedlist.SimpleLinkedList;
import java.util.EmptyStackException;

/**@author slavalymar
 * @since 10.03.2017
 * @version 1
 * @param <E>
 */
public class SimpleStack<E> extends SimpleLinkedList<E> {

    /** Pushes an item onto the top of this stack.
     * @param e
     * @return E
     */
    public E push(E e) {
        Node<E> f = first;
        Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if(f == null){
            last = newNode;
        }
        else f.prev = newNode;
        super.size++;
        return (E) newNode;
    }

    /** Removes the object at the top of this stack and
     * returns that object as the value of this function.
     * @return E
     */
    public E pop() {
        Node<E> f = first;
        return unlinkFirst(f);
    }

    /** Removes the object at the top of this stack and
     * returns that object as the value of this function.
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

    /** Looks at the object at the top of this
     * stack without removing it from the stack.
     * @return E
     */
    public E peek() {
        int var1 = super.size();
        if (var1 == 0) {
            throw new EmptyStackException();
        } else {
            return first.item == null ? null : first.item;
        }
    }

    /** Tests if this stack is empty.
     * @return boolean
     */
    public boolean empty() {
        return this.size() == 0;
    }

    /** Returns the 1-based position where an object is on this stack.
     * @param var1
     * @return int
     */
    public int search(Object var1) {
        int var2 = super.size-1;
        return var2 >= 0 ? this.size() - var2 : -1;
    }


}
