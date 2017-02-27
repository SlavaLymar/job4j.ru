package ru.lymar.testtask.player;

import java.util.Random;

/**
 * @author slavalymar
 * @since 27.02.2017
 * @version 1
 */
public class AI extends Player {

    private Random random = new Random();

    /**
     * size of field
     */
    private int size;

    public AI(String flag, String name, int size) {
        super(flag, name, size);
        this.size = size;
    }

    /** give a number
     * @param question
     * @return int
     */
    @Override
    public int getNumber(String question){
        int result = random.nextInt(this.size);
        return result;
    }
}
