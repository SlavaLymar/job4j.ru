package ru.lymar.isp.menu;

import ru.lymar.isp.action.BaseActionLevel2;
import ru.lymar.isp.input.Input;

/**
 * @author slavalymar
 * @since 22.02.2017
 * @version 1
 */
public class OneOne extends BaseActionLevel2 {

    public OneOne(String name) {
        super(name);
    }

    @Override
    public String key() {
        final String k = "1.1";
        return k;
    }


    @Override
    public void execute(Input input) {
        System.out.println("You entered OneOne");
    }


}
