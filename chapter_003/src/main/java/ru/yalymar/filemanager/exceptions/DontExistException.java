package ru.yalymar.filemanager.exceptions;

/**
 * @author slavalymar
 * @since 05.02.2017
 * @version 1
 */
public class DontExistException extends Exception{

    public DontExistException(String message) {
        super(message);
    }
}
