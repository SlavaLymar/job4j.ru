package ru.lymar.testtask;

import ru.lymar.testtask.actions.Input;
import ru.lymar.testtask.player.AI;
import ru.lymar.testtask.player.Player;
import ru.lymar.testtask.player.RealPlayer;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author slavalymar
 * @since 27.02.2017
 * @version 1
 */
public class Setting implements Input{

    private Scanner sc = new Scanner(System.in);

    /**
     * size of field
     */
    private int size = 3;

    /**
     * array of players
     */
    private Player[] players = new Player[2];

    /** get size
     * @return int
     */
    public int getSize() {
        return this.size;
    }

    /** give array of players
     * @return Player[]
     */
    public Player[] getPlayers() {
        return players;
    }

    /** give a number
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

    /**
     * ask size of field
     */
    public void askSize(){
        boolean expression = this.size < 3 || this.size > 5;
        do {
            this.size = this.getNumber("Enter size of field: ");
            if(expression) System.out.println("Field size must be from 3 to 5.");
        }
        while(expression);
    }

    /**
     * ask who will move first
     */
    public void askFirstStep(){
        int i = 1;
        String question = String.format("%s%s%s", "Enter who will have first step.",
                System.getProperty("line.separator"), "Player - 1, AI - 2.");
        boolean expression = i == 1 || i == 2;
        do {
            i = this.getNumber(question);
            if(expression) {
                if (i == 1) {
                    this.players[0] = new RealPlayer("X", "RealPlayer", this.size);
                    this.players[1] = new AI("O", "AI", this.size);
                    break;
                }
                else if (i == 2) {
                    this.players[0] = new AI("O", "AI", this.size);
                    this.players[1] = new RealPlayer("X", "RealPlayer", this.size);
                    break;
                }
            }
            System.out.println("You must be enter 1 or 2.");

        }
        while(expression);
    }

    /** choose player from players[]
     * @param i
     * @return Player
     */
    public Player choosePlayer(int i){
        return this.players[i];
    }
}
