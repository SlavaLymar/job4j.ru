package ru.lymar.testtask.actions;

import ru.lymar.testtask.player.Player;

public interface WinChecked {

    boolean winCheckedLine(Player player);

    boolean winCheckedDiagonal(Player player);
}
