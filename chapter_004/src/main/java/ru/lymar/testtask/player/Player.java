package ru.lymar.testtask.player;

import ru.lymar.testtask.actions.Turn;
import ru.lymar.testtask.actions.WinChecked;

public abstract class Player implements Turn, WinChecked{

    @Override
    public void turn() {

    }

    @Override
    public boolean winChecked() {
        return false;
    }
}