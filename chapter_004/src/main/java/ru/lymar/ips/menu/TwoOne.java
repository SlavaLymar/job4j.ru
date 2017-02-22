package ru.lymar.ips.menu;

import ru.lymar.ips.action.BaseActionLevel2;
import ru.lymar.ips.input.Input;

public class TwoOne extends BaseActionLevel2 {

    public TwoOne(String name) {
        super(name);
    }

    @Override
    public String key() {
        final String k = "2.1";
        return k;
    }

    @Override
    public void execute(Input input) {
        System.out.println("You entered TwoOne");
    }
}
