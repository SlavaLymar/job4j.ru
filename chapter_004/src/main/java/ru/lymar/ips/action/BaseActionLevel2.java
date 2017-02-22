package ru.lymar.ips.action;

public abstract class BaseActionLevel2 extends BaseAction{

    private String name;

    public BaseActionLevel2(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public String print() {
        return String.format("%4s %s", this.key(), this.name);
    }

}
