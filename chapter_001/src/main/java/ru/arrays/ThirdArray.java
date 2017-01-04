package ru.arrays;

import java.util.Arrays;

/**
 * @author slavalymar
 * @since 04.01.2017
 * @version 1
 */
public class ThirdArray {

    /**
     * @param i1
     * @param i2
     * @return result
     */
    int[] commonArr(int[] i1, int[] i2){
        int[] result = new int[i1.length+i2.length];
        while(wasArrSort(i1) && wasArrSort(i2)) {
            for(int i = 0; i<minArr(i1, i2).length;i++){
                result[i] = minArr(i1, i2)[i];
            }
            int n = 0;
            for (int i = minArr(i1, i2).length; i<result.length; i++){
                result[i] = maxArr(i1, i2)[n];
                n++;
            }
            break;
        }


        return result;
    }

    /**
     * @param arr
     * @return result
     */
    boolean wasArrSort(int[] arr){
        boolean result = false;
        int[] newArr = arr;
        Arrays.sort(newArr);
        for(int i = 0; i<arr.length; i++){
            result = arr[i] == newArr[i] ? true: false;
        }
        return result;
    }

    /**
     * @param i1
     * @param i2
     * @return i1 or i2
     */
    int[] maxArr(int[] i1, int[] i2){
        if (i1[0] > i2[0]) return i1;
        else return i2;
    }

    /**
     * @param i1
     * @param i2
     * @return i1 or i2
     */
    int[] minArr(int[] i1, int[] i2){
        if (i1[0] < i2[0]) return i1;
        else return i2;
    }
}
