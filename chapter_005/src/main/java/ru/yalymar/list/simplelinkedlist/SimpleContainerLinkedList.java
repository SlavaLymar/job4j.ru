package ru.yalymar.list.simplelinkedlist;

/** @author slavalymar
 * @since 09.03.2017
 * @version 1
 * @param <E>
 */
public interface SimpleContainerLinkedList<E>{

    /** add value to array. Extend array if one is full
     * @param e
     */
    boolean add(E e);

    /** return value if one exist
     * @param index
     * @return E
     * @throws IndexOutOfBoundsException
     */
    E get(int index);
}
