package ru.lymar.isp.menu;

import ru.lymar.isp.input.Input;

/**
 * @author slavalymar
 * @since 03.03.2017
 * @version 1
 */
public class Exit extends Item {

    /**
     * name of item
     */
    private String name;

    /**
     * instance of menu
     */
    private Menu menu;

    public Exit(String name, Menu menu) {
        super(name);
        this.name = name;
        this.menu = menu;
    }

    /** execute something
     * @param input
     */
    @Override
    public void execute(Input input) {
        System.out.println("Bye!");
    }

    /**
     * print item depends of level
     */
    @Override
    public void print() {
        this.menu.setB(false);
    }

    /** return number of menu
     * @return String
     */
    @Override
    public String getKey() {
        return this.name;
    }
}
