package ru.lymar.srp.calculate;

/**
 * @author slavalymar
 * @since 15.02.2017
 * @version 1
 */
public class Tangent implements Calculate{

    /** calculate tangent
     * @param a
     * @param b
     * @return float
     */
    @Override
    public float execute(float a, float b) {
        return (float) Math.round(Math.tan(Math.toRadians(a)));
    }
}
