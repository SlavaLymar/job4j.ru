package ru.yalymar.iterator.simplenumbers;

import java.util.Iterator;

/**
 * @author slavalymar
 * @since 28.02.2017
 * @version 1
 */
public class SimpleNumbers implements Container {

    /**
     * array of numbers
     */
    private final int[] numbers;

    public SimpleNumbers(final int[] numbers) {
        this.numbers = numbers;
    }

    public int[] getNumbers() {
        return this.numbers;
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
            if(this.checkSimple()) return true;
            return false;
        }

        /** give true if a number is simple
         * @return boolean
         */
        private boolean checkSimple() {

            boolean result = false;

            if(numbers[this.index] <= 1) return result;
            if(numbers[this.index] == 2 || numbers[this.index] == 3) {
                result = true;
                return result;
            }
            else {
                for(int i = 2; i*i <= numbers[this.index]; i++){
                    if(numbers[this.index]%i == 0){
                        result = false;
                        return result;
                    }
                    else {
                        result = true;
                    }
                }
                return result;
            }
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
