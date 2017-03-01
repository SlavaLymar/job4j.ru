package ru.yalymar.iterator.evennumbers;

import java.util.Iterator;

/**
 * @author slavalymar
 * @since 28.02.2017
 * @version 1
 */
public class Numbers implements Container {

    /**
     * array of numbers
     */
    private final int[] numbers;

    public Numbers(final int[] numbers) {
        this.numbers = numbers;
    }

    public int[] getNumbers() {
        return numbers;
    }

    /** get Iterator
     * @return Iterator
     */
    @Override
    public Iterator getIterator() {
        return new EvenNumbersIterator();
    }


    private class EvenNumbersIterator implements Iterator{

        /**
         * index of container array
         */
        private int index = 0;

        /** has next object
         * @return boolean
         */
        @Override
        public boolean hasNext() {
            if(this.index < numbers.length && numbers[this.index]%2 == 0) return true;
            return false;
        }

        /** next object
         * @return Object
         */
        @Override
        public Object next() {
            if(this.hasNext()) return numbers[this.index++];
            else{
                this.index++;
                return -1;
            }

        }
    }


}
