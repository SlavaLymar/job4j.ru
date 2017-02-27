package ru.lymar.testtask.player;

public abstract class Player{

    private String flag;
    private String name;

    public Player(String flag, String name) {
        this.flag = flag;
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public String getName() {
        return name;
    }
}