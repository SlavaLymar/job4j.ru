package ru.factorial;

/**
 * @author slavalymar
 * @since 02.01.2017
 * @version 1
 */
public class Factorial {

    /**
     * @param value
     * @return result
     */
    int factorial(int value){
        int result = 1;
        for(int i = 1; i<=value; i++) {
            result *= i;
        }
        return result;

    }
}
