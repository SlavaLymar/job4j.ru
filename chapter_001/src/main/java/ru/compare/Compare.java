package ru.compare;

import java.util.Arrays;

/** @author slavalymar
 * @since 03.01.2017
 * @version 1
 */
public class Compare {

    /**
     * @param arr
     * @return result
     */
    String[] deleteArr(String[] arr){
        int[] duplicate = new int[arr.length];
        for(int i = 0; i<arr.length-1; i++){
            for(int j = i+1; j<arr.length; j++){
                if(arr[i].equals(arr[j])){
                    duplicate[j] = j;
                }
            }
        }

        String[] result = Arrays.copyOf(arr, arr.length-countMethod(arr));
        for (int i = 0; i<result.length; i++){
            for(int j = 0; j<duplicate.length; j++){
                if(duplicate[j] == 0){
                    result[i] = arr[j];
                    duplicate[j] = 1;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * @param arr
     * @return count
     */
    int countMethod(String[] arr){
        int count = 0;
        for(int i = 0; i<arr.length-1; i++){
            for(int j = i+1; j<arr.length; j++){
                if(arr[i].equals(arr[j])){
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}
