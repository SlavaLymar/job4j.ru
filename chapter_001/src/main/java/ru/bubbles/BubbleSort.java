package ru.bubbles;

/**
 * @author slavalymar
 * @since 02.01.2017
 * @version 1
 */
public class BubbleSort {

    /**
     * @param oldarr
     * @return result
     */
    int[] bubbleSort(int[] oldarr){
        int[] result = oldarr;
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
