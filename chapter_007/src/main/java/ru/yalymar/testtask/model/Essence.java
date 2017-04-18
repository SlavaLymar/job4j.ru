package ru.yalymar.testtask.model;

import ru.yalymar.testtask.field.Field;
import ru.yalymar.testtask.mechanic.Mobility;
import ru.yalymar.testtask.mechanic.Randomize;

/**
 * @author slavalymar
 * @since 16.04.2017
 * @version 1
 */
public abstract class Essence implements Mobility{

    /**
     * name of essence
     */
    private final String name;

    protected int x;
    protected int y;

    /**
     * field`s instance
     */
    protected Field field;

    /**
     * randomize instance
     */
    protected Randomize r;

    /**
     * flag of life
     */
    private boolean isAlive;

    public Essence(String name, Field field) {
        this.name = name;
        this.field = field;
        this.isAlive = true;
        this.initR();
    }

    /**
     * determines randomize`s instance
     */
    private void initR() {
        this.r = () -> this.field.getRandom().nextInt(3) - 1;
    }

    /** to make move
     * @param newX
     * @param newY
     */
    @Override
    public void move(int newX, int newY) {
        this.field.getCells()[newY][newX].setE(this);
        this.field.getCells()[this.y][this.x].delete();
        this.setX(newX);
        this.setY(newY);
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    public String getName() {
        return this.name;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Field getField() {
        return this.field;
    }
}
