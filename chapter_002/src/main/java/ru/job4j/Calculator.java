package ru.job4j;

/** @author slavalymar
 * @since 29/12/2016
 * @version 1
*/

public class Calculator {
    private double result;

    /**
     * method add
     * @param first
     * @param second
     */
    void add(double first, double second){
        this.result = first + second;
    }

    /**
     * method subctruct
     * @param first
     * @param second
     */
    void substruct(double first, double second){
        this.result = first - second;
    }

    /**
     * method div
     * @param first
     * @param second
     */
    void div(double first, double second){
        this.result = first / second;
    }

    /**
     * method multiple
     * @param first
     * @param second
     */
    void multiple(double first, double second){
        this.result = first * second;
    }

    /**
     * method getResult
     * @return result
     */
    public double getResult() {
        return this.result;
    }
}
