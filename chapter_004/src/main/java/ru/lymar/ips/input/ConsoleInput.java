package ru.lymar.ips.input;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleInput implements Input {

    private Scanner sc = new Scanner(System.in);

    @Override
    public String ask(String question){
        System.out.println(question);
        String result = sc.nextLine();
        return result;
    }

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
