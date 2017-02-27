package ru.lymar.testtask.exception;

/**
 * @author slavalymar
 * @since 27.02.2017
 * @version 1
 */
public class OutOfRangeException extends Exception{

    /** value is out of range of field
     * @param message
     */
    public OutOfRangeException(String message) {
        super(message);
    }
}
