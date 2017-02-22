package ru.lymar.isp.input;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author slavalymar
 * @since 22.02.2017
 * @version 1
 */
public class ConsoleInput implements Input {

    private Scanner sc = new Scanner(System.in);

    /**
     * @param question
     * @return String
     */
    @Override
    public String ask(String question){
        System.out.println(question);
        String result = sc.nextLine();
        return result;
    }

    /**
     * @param question
     * @param list
     * @return String
     */
    @Override
    public String ask(String question, List <String> list){
        String result = this.ask(question);
        for(String s: list){
            if(s.equals(result)){
                break;
            }
        }
        return result;
    }

    /**
     * @param question
     * @return int
     */
    @Override
    public int getNumber(String question) {
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
