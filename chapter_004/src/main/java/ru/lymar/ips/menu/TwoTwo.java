package ru.lymar.ips.menu;

import ru.lymar.ips.action.BaseActionLevel2;
import ru.lymar.ips.input.Input;

public class TwoTwo extends BaseActionLevel2 {

    public TwoTwo(String name) {
        super(name);
    }

    @Override
    public String key() {
        final String k = "2.2";
        return k;
    }

    @Override
    public void execute(Input input) {
        System.out.println("You entered TwoTwo");
    }
}
