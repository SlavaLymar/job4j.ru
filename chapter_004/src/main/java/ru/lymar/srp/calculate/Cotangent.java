package ru.lymar.srp.calculate;

/**
 * @author slavalymar
 * @since 15.02.2017
 * @version 1
 */
public class Cotangent implements Calculate{

    /** calculate cotangent
     * @param a
     * @param b
     * @return float
     */
    @Override
    public float execute(float a, float b) {

        return (float) (1/Math.tan(Math.toRadians(a)));
    }
}
