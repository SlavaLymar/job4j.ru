package ru.testtask;

/**
 * @author slavalymar
 * @since 04.01.2017
 * @version 1
 */
public class Testtask {

    /**
     * @param origin
     * @param sub
     * @return result
     */
    boolean contains(String origin, String sub){

        boolean result = false;
        for(int i = 0; i<=origin.length()-sub.length();i++){
            if(origin.substring(i, i+sub.length()).equals(sub)){
                result = true;
                break;
            }
        }

        return result;
    }
}
