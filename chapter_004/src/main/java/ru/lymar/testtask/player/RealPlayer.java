package ru.lymar.testtask.player;

import ru.lymar.testtask.actions.Input;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RealPlayer extends Player implements Input{

    private Scanner sc = new Scanner(System.in);

    public RealPlayer(String flag, String name) {
        super(flag, name);
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
