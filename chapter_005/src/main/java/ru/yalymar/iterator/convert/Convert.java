package ru.yalymar.iterator.convert;

import java.util.Iterator;

/**
 * @author slavalymar
 * @since 01.03.2017
 * @version 1
 */
public interface Convert {

    /** convert several iterators to one iterator
     * @param it
     * @return Iterator
     */
    Iterator <Integer> convert(Iterator <Iterator <Integer>> it);
}
