package ru.lymar.testtask.actions;

import ru.lymar.testtask.field.Cell;

/**
 * @author slavalymar
 * @since 27.02.2017
 * @version 1
 */
public interface Print {

    /**
     * print field
     */
    void print(Cell[][] cells, String[] numbers);
}
