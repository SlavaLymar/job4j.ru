package ru.yalymar.iterator.convert;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author slavalymar
 * @since 01.03.2017
 * @version 1
 */
public class ConvertIterators implements Convert{

    /**
     * an iterator consist of several iterators
     */
    private GeneralizedIterator generalizedIterator
            = new GeneralizedIterator(new ArrayList<>());

    /**
     * collection of iterators
     */
    private Iterator<Iterator<Integer>> it;

    /** convert several iterators to one iterator
     * @param it
     * @return Iterator
     */
    @Override
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        this.it = it;
        if(this.it != null){
            while(this.it.hasNext()) {
                Iterator<Integer> tmpIterator = this.it.next();
                while (tmpIterator.hasNext()) {
                    this.generalizedIterator.getIntegers().add(tmpIterator.next());
                }
            }
        }
        return this.generalizedIterator;
    }
}
