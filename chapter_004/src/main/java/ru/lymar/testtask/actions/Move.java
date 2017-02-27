package ru.lymar.testtask.actions;

import ru.lymar.testtask.exception.CellIsOccupiedException;
import ru.lymar.testtask.player.Player;

/**
 * @author slavalymar
 * @since 27.02.2017
 * @version 1
 */
public interface Move {

    /** make a move
     * @param x
     * @param y
     * @param player
     * @return boolean
     * @throws CellIsOccupiedException
     */
    boolean move(int x, int y, Player player) throws CellIsOccupiedException;
}
