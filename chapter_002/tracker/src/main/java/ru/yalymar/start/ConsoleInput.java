package ru.yalymar.start;

import ru.yalymar.exceptions.MenuOutOfException;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author slavalymar
 * @since 13.01.2017
 * @version 1
 */
public class ConsoleInput implements Input {
    private Scanner sc = new Scanner(System.in);

    /**
     * @param question
     * @return result
     */
    @Override
    public String ask(String question){
        System.out.println(question);
        String result = sc.nextLine();
        return result;
    }

    /**
     * @param question
     * @param arrLength
     * @return result
     */
    @Override
    public int ask(String question, int[] arrLength) {
        int result = 0;
        int number = Integer.parseInt(this.ask(question));
        boolean exist = false;
        for(int i: arrLength){
            if(i == number-1){
                result = i;
                exist = true;
                break;
            }
        }
        if(exist)return result;
        else {
            throw new MenuOutOfException("Out of range");
        }
    }

    /**
     * @param question
     * @return result
     */
    @Override
    public int getNumber(String question) throws InputMismatchException {
        System.out.println(question);
        int result = 0;
        try {
            result = sc.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Incorrect statement!!");
        }
        sc.nextLine();
        return result;
    }



}
