package ru.lymar.ips.menu;

import ru.lymar.ips.action.BaseActionLevel2;
import ru.lymar.ips.input.Input;

public class OneTwo extends BaseActionLevel2{

    public OneTwo(String name) {
        super(name);
    }

    @Override
    public String key() {
        final String k = "1.2";
        return k;
    }

    @Override
    public void execute(Input input) {
        System.out.println("You entered OneTwo");
    }
}
