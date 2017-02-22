package ru.lymar.ips.action;

public abstract class BaseActionLevel1 extends BaseAction{

    private String name;

    public BaseActionLevel1(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public String print() {
        return String.format("%s %s", this.key(), this.name);
    }
}
