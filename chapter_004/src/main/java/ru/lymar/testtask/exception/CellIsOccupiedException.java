package ru.lymar.testtask.exception;

/**
 * @author slavalymar
 * @since 27.02.2017
 * @version 1
 */
public class CellIsOccupiedException extends Exception {

    /** cell is occupied
     * @param message
     */
    public CellIsOccupiedException(String message) {
        super(message);
    }
}
