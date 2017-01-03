package ru.bubbles;

/**
 * @author slavalymar
 * @since 02.01.2017
 * @version 1
 */
public class BubbleSort {


    /**
     * @param result
     * @return result
     */
    int[] bubbleSort(int[] result){

        for(int i = result.length-1; i>0; i--){
            for (int j = 0; j<i; j++){
                if(result[j] > result[j+1]){
                    int tmp = result[j];
                    result[j] = result[j+1];
                    result[j+1] = tmp;
                }
            }
        }
        return result;
    }
}
