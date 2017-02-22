package ru.lymar.ips.menu;

import ru.lymar.ips.action.BaseActionLevel2;
import ru.lymar.ips.input.Input;

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
