package ru.lymar.ips.menu;

import ru.lymar.ips.action.BaseActionLevel1;
import ru.lymar.ips.input.Input;

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
