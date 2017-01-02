package ru.turn;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author slavalymar
 * @since 02.01.2017
 * @version 1
 */
public class Turn {

    /**
     * @param getarr
     * @return result
     */
    int[] back(int[] getarr){
        int[] arrtmp = getarr;
        int[] result = new int[getarr.length];
        for(int i = 0; i<arrtmp.length; i++){
            result[i] = arrtmp[arrtmp.length-(i+1)];
        }
        return result;
    }
}
