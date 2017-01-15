package ru.compare;

import java.util.Arrays;

/** @author slavalymar
 * @since 03.01.2017
 * @version 1
 */
public class Compare {

    /**
     * @param arr1, arr2
     * @return result
     */
    private String[] delArray(String[] arr1, String[] arr2) {
        int ind = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != null) {
                arr2[ind] = arr1[i];
                ind++;
            }
        }
        return arr2;
    }


    /**
     * @param arr
     * @return delArray()
     */
    String[] countMethod(String[] arr){
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == null) {
                continue;
            }
            if (count == arr.length - 1) {
                break;
            }
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i].equals(arr[j])) {
                    arr[j] = null;
                    count++;
                }
            }
        }
        String[] expectArray = new String[arr.length - count];
        return delArray(arr, expectArray);
    }
}
