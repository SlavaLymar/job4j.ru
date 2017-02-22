package ru.lymar.ips.action;

public abstract class BaseActionLevel3 extends BaseAction{

    private String name;

    public BaseActionLevel3(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public String print() {
        return String.format("%8s %s", this.key(), this.name);
    }
}
