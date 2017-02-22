package ru.lymar.ips.action;

public abstract class BaseAction implements UserAction{

    private String name;

    public BaseAction(String name) {
        this.name = name;
    }
}
