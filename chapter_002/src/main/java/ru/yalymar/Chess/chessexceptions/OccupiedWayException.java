package ru.yalymar.Chess.chessexceptions;

/**
 * @author slavalymar
 * @since 23.01.2017
 * @version 1
 */
public class OccupiedWayException extends Exception {
    public OccupiedWayException(String message) {
        super(message);
    }
}
