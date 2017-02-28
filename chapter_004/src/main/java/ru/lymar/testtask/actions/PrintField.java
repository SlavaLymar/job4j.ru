package ru.lymar.testtask.actions;

import ru.lymar.testtask.field.Cell;

/**
 * @author slavalymar
 * @since 27.02.2017
 * @version 1
 */
public class PrintField implements Print {

    /**
     * print field
     */
    @Override
    public void print(Cell[][] cells, String[] numbers) {
        for (int k = 0; k<cells.length; k++) {
            System.out.print(String.format("  %s", numbers[k]));
        }
        System.out.println(String.format("  %s", "Y"));

        for (int i = 0; i<cells.length; i++){
            System.out.print(String.format("%s ", numbers[i]));
            for (int j = 0; j<cells.length; j++){
                System.out.print(String.format("%s  ", cells[i][j].getValue()));
            }
            System.out.println();
        }
        System.out.println(String.format("%s", "X"));
    }
}
