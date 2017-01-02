package ru.counter;

/**
 * @autor slavalymar
 * @since 02.01.2017
 * @version 1
 */
public class Counter {

    /**
     * @param start
     * @param finish
     * @return result
     */
    public int add(int start, int finish){
        int result = 0;
        for(int i = start; i <= finish; i++ ){
          if(i%2==0){
              result += i;
          }
        }
        return result;
    }
}
