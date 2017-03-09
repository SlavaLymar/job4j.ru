package ru.yalymar.iterator.doublearrayiterator;

import java.util.Iterator;

/**
 * @author slavalymar
 * @since 09.03.2017
 * @version 1
 */
public class DoubleArray {

    /**
     * array of integers
     */
    private final int[][] numbers;

    public DoubleArray(final int[][] numbers) {
        this.numbers = numbers;
    }

    /** return Iterator
     * @return Iterator
     */
    public Iterator getIterator() {
        return new DoubleArray.DoubleArrayIterator();
    }

    /**
     * private class implements Iterator
     */
    private class DoubleArrayIterator implements Iterator{

        /**
         * indexes of array
         */
        private int yIndex = 0, xIndex = 0;

        /** if value is exist return true
         * @return boolean
         */
        @Override
        public boolean hasNext() {
            if(yIndex < numbers.length && xIndex < numbers[0].length){
                return true;
            }
            return false;
        }

        /** return value
         * @return Integer
         */
        @Override
        public Integer next() {
            if(xIndex == numbers[0].length){
                xIndex = 0;
                yIndex++;
            }
            if(this.hasNext()){
                return numbers[yIndex][xIndex++];
            }
            return null;
        }
    }

}
