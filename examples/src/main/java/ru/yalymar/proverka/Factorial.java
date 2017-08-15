package ru.yalymar.proverka;

/**
 * Class Класс для вычисления факториала числа.
 *
 * @author shkredr
 * @version 1.0
 * @since 24.07.2017
 */
public class Factorial {
    public int[] back(int[] array) {
//        int temp;
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }

    public static void main(String[] args) {
        Factorial f = new Factorial();
        int[] a = {1, 2, 3, 4, 5};
        int[] res = f.back(a);
        for(int i = 0; i < res.length; i++){
            System.out.println(res[i]);
        }
    }
}