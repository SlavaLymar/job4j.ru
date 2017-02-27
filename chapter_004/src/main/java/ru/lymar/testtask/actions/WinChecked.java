package ru.lymar.testtask.actions;

import ru.lymar.testtask.player.Player;

/**
 * @author slavalymar
 * @since 27.02.2017
 * @version 1
 */
public interface WinChecked {

    /** checked line
     * @param player
     * @return boolean
     */
    boolean winCheckedLine(Player player);

    /** checked diagonal
     * @param player
     * @return boolean
     */
    boolean winCheckedDiagonal(Player player);
}
