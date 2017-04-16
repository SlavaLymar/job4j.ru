package ru.yalymar.testtask.mechanic;

public interface Mobility {

    int[] createMove() throws InterruptedException;

    void move(int x, int y);
}
