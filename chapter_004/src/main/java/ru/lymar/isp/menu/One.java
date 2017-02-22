package ru.lymar.isp.menu;

import ru.lymar.isp.action.BaseActionLevel1;
import ru.lymar.isp.input.Input;

/**
 * @author slavalymar
 * @since 22.02.2017
 * @version 1
 */
public class One extends BaseActionLevel1{

    public One(String name) {
        super(name);
    }

    @Override
    public String key() {
        final String k = "1";
        return k;
    }

    @Override
    public void execute(Input input) {
        System.out.println("You entered One");
    }


}
