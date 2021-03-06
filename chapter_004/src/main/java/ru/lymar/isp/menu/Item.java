package ru.lymar.isp.menu;

import ru.lymar.isp.action.UserAction;
import ru.lymar.isp.input.Input;

/**
 * @author slavalymar
 * @since 03.03.2017
 * @version 1
 */
public class Item implements UserAction{

    /**
     * name of item
     */
    private String name;

    /**
     * array of item
     */
    private Item[] items;

    public Item(String name, Item... items) {
        this.name = name;
        this.items = items;
    }

    public Item[] getItems() {
        return this.items;
    }

    /** execute something
     * @param input
     */
    @Override
    public void execute(Input input) {
        System.out.println("You picked \""+ this.name +"\"");
    }

    /**
     * print item depends of level
     */
    @Override
    public void print() {
        System.out.println(this.name);
    }

    /** return number of menu
     * @return String
     */
    @Override
    public String getKey() {
        String[] s = this.name.split(" ");
        return s[0];
    }

}
