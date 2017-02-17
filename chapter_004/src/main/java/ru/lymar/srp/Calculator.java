package ru.lymar.srp;

import ru.lymar.srp.calculate.Calculate;

/**
 * @author slavalymar
 * @since 15.02.2017
 * @version 1
 */
public class Calculator {

    private Calculate calculate;

    /** constructor of calculator
     * @param calculate
     */
    public Calculator(Calculate calculate) {

        this.calculate = calculate;
    }

    /** select action
     * @param a
     * @param b
     * @return float
     */
    public float action(float a, float b){

        return calculate.execute(a,b);
    }



}
