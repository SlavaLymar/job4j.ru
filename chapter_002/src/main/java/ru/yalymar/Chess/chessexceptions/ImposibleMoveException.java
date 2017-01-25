package ru.yalymar.chess.chessexceptions;

/**
 * @author slavalymar
 * @since 23.01.2017
 * @version 1
 */
public class ImposibleMoveException extends Exception{

    public ImposibleMoveException(String message) {
        super(message);
    }
}
