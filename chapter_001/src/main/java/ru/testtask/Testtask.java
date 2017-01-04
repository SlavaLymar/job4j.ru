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
        char[] charOrigin = origin.toCharArray();
        String[] arr = new String[origin.length()-sub.length()+1];

        for(int i = 0; i<arr.length; i++){
            arr[i] = new String(charOrigin, i, sub.length());
        }

        for (String str : arr){
            if(str.equals(sub)) result = true;
        }

        return result;
    }
}
