package ru.lymar.testtask.player;

import ru.lymar.testtask.actions.Input;

/**
 * @author slavalymar
 * @since 27.02.2017
 * @version 1
 */
public abstract class Player implements Input{

    /**
     * flag for game
     */
    private String flag;

    /**
     * name of player
     */
    private String name;

    /**
     * size of field
     */
    private int size;

    public Player(String flag, String name, int size) {
        this.flag = flag;
        this.name = name;
        this.size = size;
    }

    /** get flag
     * @return String
     */
    public String getFlag() {
        return flag;
    }

    /** get name
     * @return String
     */
    public String getName() {
        return name;
    }
}