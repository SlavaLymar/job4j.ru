package ru.yalymar.list.simplestack;

import ru.yalymar.list.simplelinkedlist.SimpleLinkedList;
import java.util.EmptyStackException;

public class SimpleStack<E> extends SimpleLinkedList<E> {

    public E push(E var1) {
        this.add(var1);
        return var1;
    }

    public E pop() {
        Node<E> f = first;
        return unlinkFirst(f);
    }

    protected E unlinkFirst(Node<E> f) {
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return element;
    }

    public E peek() {
        int var1 = this.size();
        if (var1 == 0) {
            throw new EmptyStackException();
        } else {
            return super.get(super.size-1);
        }
    }

    public boolean empty() {
        return this.size() == 0;
    }

    public int search(Object var1) {
        int var2 = super.size-1;
        return var2 >= 0 ? this.size() - var2 : -1;
    }


}
