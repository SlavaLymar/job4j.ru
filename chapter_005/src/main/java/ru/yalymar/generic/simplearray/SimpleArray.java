package ru.yalymar.generic.simplearray;

/**
 * @author slavalymar
 * @since 06.03.2017
 * @version 1
 * @param <T>
 */
public class SimpleArray <T> {

    /**
     * array of objects
     */
    T[] arr;

    /**
     * index of array
     */
    int index = 0;

    public SimpleArray(T[] arr) {
        this.arr = arr;
    }

    /** return array
     * @return T[]
     */
    public T[] getArr() {
        return this.arr;
    }

    /** add value to array
     * @param value
     */
    public void add(T value){
        this.arr[index++] = value;
    }

    /** update value in array
     * @param position
     * @param value
     */
    public void update(int position, T value){
        this.arr[position] = value;
    }

    /** get value from array
     * @param position
     * @return T
     */
    public T get(int position){
        return this.arr[position];
    }

    /** delete value from array
     * @param position
     */
    public void delete(int position){
        this.arr[position] = null;
    }

}
