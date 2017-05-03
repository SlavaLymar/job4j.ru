package ru.yalymar.testtask0.mechanic;

/**
 * @author slavalymar
 * @since 16.04.2017
 * @version 1
 */
public interface Mobility {

    /** create move
     * @return int[]
     * @throws InterruptedException
     */
    int[] createMove() throws InterruptedException;

    /** to make move
     * @param x
     * @param y
     */
    void move(int x, int y);
}
