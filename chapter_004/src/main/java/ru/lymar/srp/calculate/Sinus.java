package ru.lymar.srp.calculate;

/**
 * @author slavalymar
 * @since 15.02.2017
 * @version 1
 */
public class Sinus implements Calculate{

    /** calculate sinus
     * @param a
     * @param b
     * @return float
     */
    @Override
    public float execute(float a, float b) {
        return (float) Math.round(Math.sin(Math.toRadians(a)));
    }
}
