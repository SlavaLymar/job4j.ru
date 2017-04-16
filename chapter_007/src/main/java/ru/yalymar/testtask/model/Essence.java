package ru.yalymar.testtask.model;

import ru.yalymar.testtask.field.Field;
import ru.yalymar.testtask.mechanic.Mobility;
import ru.yalymar.testtask.mechanic.Randomize;

public abstract class Essence implements Mobility{

    private final String name;
    protected int x;
    protected int y;
    protected Field field;
    protected Randomize r;
    private boolean isAlive;

    public Essence(String name, Field field) {
        this.name = name;
        this.field = field;
        this.isAlive = true;
        this.initR();
    }

    private void initR() {
        this.r = () -> this.field.getRandom().nextInt(3) - 1;
    }

    @Override
    public void move(int newX, int newY) {
        this.field.getCells()[newY][newX].setE(this);
        this.field.getCells()[y][x].delete(this);
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
}
