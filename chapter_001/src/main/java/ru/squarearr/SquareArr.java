package ru.squarearr;

import java.util.Arrays;

/**
 * @author slavalymar
 * @since 02.01.2017
 * @version 1
 */
public class SquareArr {

    /**
     * @param getarr
     * @return result
     */
    int[][] turn(int[][] getarr){
        int[][] result = getarr;
        for (int i = 0; i<result.length/2; i++){
            for (int j = i; j<result.length-i-1; j++){
                int tmp = result[i][j];
                result[i][j] = result[j][result.length-1-i];
                result[j][result.length-1-i] = result[result.length-1-i][result.length-1-j];
                result[result.length-1-i][result.length-1-j] = result[result.length-1-j][i];
                result[result.length-1-j][i] = tmp;
            }
        }
        return result;
    }
}
