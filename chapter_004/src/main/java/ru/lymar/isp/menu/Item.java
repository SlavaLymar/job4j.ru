package ru.lymar.isp.menu;

import ru.lymar.isp.action.BaseAction;
import ru.lymar.isp.input.Input;

/**
 * @author slavalymar
 * @since 03.03.2017
 * @version 1
 */
public class Item extends BaseAction{

    /**
     * level of item
     */
    private int level;

    /**
     * name of item
     */
    private String name;

    public Item(String name, int level) {
        super(name);
        this.level = level;
        this.name = name;
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
        int count = 0;
        while(count != this.level){
            System.out.print(" ");
            count++;
        }
        System.out.println(this.name);
    }
}
