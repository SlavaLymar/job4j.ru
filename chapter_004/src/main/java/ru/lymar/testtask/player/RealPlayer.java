package ru.lymar.testtask.player;

import ru.lymar.testtask.exception.OutOfRangeException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author slavalymar
 * @since 27.02.2017
 * @version 1
 */
public class RealPlayer extends Player{

    private Scanner sc = new Scanner(System.in);

    /**
     * size of field
     */
    private int size;

    public RealPlayer(String flag, String name, int size) {
        super(flag, name, size);
        this.size = size;
    }

    /** give a number
     * @param question
     * @return int
     */
    @Override
    public int getNumber(String question){
        System.out.println(question);
        int result = 0;
        boolean exit = true;
        do {
            try {
                result = sc.nextInt();
                sc.nextLine();
                if (this.exit(result)) {
                    exit = false;
                }
            }
            catch (OutOfRangeException e) {
                String str = String.format("%s%s%s",
                        "You should enter number between 0 -", this.size-1, "!");
                System.out.println(str);
            }
            catch (InputMismatchException e){
                System.out.println("You should enter a number!");
            }
        }
        while(exit);
        return result;
    }

    /** checked input
     * @param i
     * @return boolean
     * @throws OutOfRangeException
     * @throws InputMismatchException
     */
    private boolean exit(int i) throws OutOfRangeException, InputMismatchException {
        if ((i >= this.size || i < 0)){
            throw new OutOfRangeException("Out of range!");
        }
        return true;
    }

}
