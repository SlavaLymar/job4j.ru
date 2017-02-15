package ru.lymar.srp.calculate;

/**
 * @author slavalymar
 * @since 15.02.2017
 * @version 1
 */
public class Addiction implements Calculate{

    /** add operation
     * @param a
     * @param b
     * @return float
     */
    @Override
    public float execute(float a, float b) {
        return a+b;
    }


}
