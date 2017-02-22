package ru.lymar.ips.menu;

import ru.lymar.ips.action.BaseActionLevel2;
import ru.lymar.ips.input.Input;

public class TwoThree extends BaseActionLevel2 {

    public TwoThree(String name) {
        super(name);
    }

    @Override
    public String key() {
        final String k = "2.3";
        return k;
    }

    @Override
    public void execute(Input input) {
        System.out.println("You entered TwoThree");
    }
}
