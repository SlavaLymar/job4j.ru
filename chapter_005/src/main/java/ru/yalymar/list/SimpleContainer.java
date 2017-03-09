package ru.yalymar.list;

/** @author slavalymar
 * @since 09.03.2017
 * @version 1
 * @param <E>
 */
public interface SimpleContainer<E> extends Iterable{

    /** add value to array. Extend array if one is full
     * @param e
     */
    void add(E e);

    /** return value if one exist
     * @param index
     * @return E
     * @throws IndexOutOfBoundsException
     */
    E get(int index);
}
