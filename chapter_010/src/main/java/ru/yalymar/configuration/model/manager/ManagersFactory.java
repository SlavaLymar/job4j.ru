package ru.yalymar.configuration.model.manager;

public class ManagersFactory {

    private final ItemManager im = new ItemManager();

    public ItemManager getIm() {
        return this.im;
    }
}
