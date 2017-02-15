package ru.lymar.srp.input;

import java.util.Scanner;

public class Input {

    protected Scanner sc = new Scanner(System.in);

    public String readConsole() {

        String result = null;
        do {
            result = sc.nextLine();
        }
        while (!sc.hasNext());
        return result;
    }
}
