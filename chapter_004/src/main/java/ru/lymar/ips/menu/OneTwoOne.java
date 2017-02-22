package ru.lymar.ips.menu;

import ru.lymar.ips.action.BaseActionLevel3;
import ru.lymar.ips.input.Input;

public class OneTwoOne extends BaseActionLevel3 {

    public OneTwoOne(String name) {
        super(name);
    }

    @Override
    public String key() {
        final String k = "1.2.1";
        return k;
    }

    @Override
    public void execute(Input input) {
        System.out.println("You entered OneTwoOne");
    }
}
