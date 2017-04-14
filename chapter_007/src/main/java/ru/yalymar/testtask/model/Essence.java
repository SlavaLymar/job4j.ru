package ru.yalymar.testtask.model;

import ru.yalymar.testtask.mechanic.Mobility;

public abstract class Essence implements Mobility{

    private final String name;

    public Essence(String name) {
        this.name = name;
    }

    @Override
    public void move() {

    }
}
