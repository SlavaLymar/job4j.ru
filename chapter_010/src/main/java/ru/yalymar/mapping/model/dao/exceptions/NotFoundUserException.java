package ru.yalymar.mapping.model.dao.exceptions;

public class NotFoundUserException extends Exception {

    public NotFoundUserException(String message) {
        super(message);
    }
}
