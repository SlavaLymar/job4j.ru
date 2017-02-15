package ru.lymar.srp.input;

import java.util.Scanner;

/**
 * @author slavalymar
 * @since 15.02.2017
 * @version 1
 */
public class Input {

    private Scanner sc;

    public Input(Scanner sc) {
        this.sc = sc;
    }

    /** read from console
     * @return String
     */
    public String readConsole() {
        String result = sc.nextLine();
        return result;
    }
}
