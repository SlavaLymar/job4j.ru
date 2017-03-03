package ru.yalymar.iterator.convert;

import java.util.Iterator;

/**
 * @author slavalymar
 * @since 03.03.2017
 * @version 1
 */
public class ConvertIterators implements Convert{

    /** convert several iterators to one iterator
     * @param it
     * @return Iterator
     */
    @Override
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new SingleIterator(it);
    }

    /**
     * @author slavalymar
     * @since 01.03.2017
     * @version 1
     */
    private class SingleIterator implements Iterator<Integer> {

        /**
         * Iterator iterators
         */
        private Iterator<Iterator<Integer>> it;

        /**
         * Iterator of iterator iterators
         */
        private Iterator <Integer> iterator;

        public SingleIterator(Iterator<Iterator<Integer>> it) {
            this.it = it;
            if(this.it.hasNext()){
                this.iterator = this.it.next();
            }
        }

        /** determines if an iterator is exist into iterator iterators
         * @return boolean
         */
        @Override
        public boolean hasNext() {
            return this.it.hasNext();
        }

        /** return next value of iterator of iterator of iterators
         * @return Integer
         */
        @Override
        public Integer next() {
            if(this.iterator == null){
                this.iterator = this.it.next();
            }
            else if(!this.iterator.hasNext()){
                this.iterator = this.it.next();
            }
            if(this.iterator.hasNext()){
                return this.iterator.next();
            }
            else {
                this.iterator = this.it.next();
                return this.iterator.next();
            }
        }

    }


}
