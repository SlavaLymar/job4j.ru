package ru.yalymar.configuration.model.manager;

/**
 * @author slavalymar
 * @since 13.06.2017
 * @version 1
 */
public class ManagersFactory {

    private final ItemManager im = new ItemManager();

    public ItemManager getIm() {
        return this.im;
    }
}
