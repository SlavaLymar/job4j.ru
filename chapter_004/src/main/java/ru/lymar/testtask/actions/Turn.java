package ru.lymar.testtask.actions;

import ru.lymar.testtask.exception.CellIsOccupiedException;
import ru.lymar.testtask.player.Player;

public interface Turn {

    void turn(int x, int y, Player player) throws CellIsOccupiedException;
}
