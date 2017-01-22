package ru.yalymar.start;

import ru.yalymar.exceptions.MenuOutOfException;

public class ValidateInput extends ConsoleInput{

    @Override
    public int ask(String question, int[] arrLength){
        boolean exit = true;
        int value = -1;

        do {
            try {
                value = super.ask(question, arrLength);
                exit = false;
            }
            catch (MenuOutOfException e){
                System.out.println("Select exist number of menu!!");
            }
            catch (NumberFormatException e){
                System.out.println("Enter a number!!!");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        while(exit);
        return value;
    }
}
