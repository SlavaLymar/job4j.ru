package ru.lymar.isp.menu;

import ru.lymar.isp.action.BaseActionLevel1;
import ru.lymar.isp.input.Input;

/**
 * @author slavalymar
 * @since 22.02.2017
 * @version 1
 */
public class Two extends BaseActionLevel1{

    public Two(String name) {
        super(name);
    }

    @Override
    public String key() {
        final String k = "2";
        return k;
    }

    @Override
    public void execute(Input input){
        System.out.println("You entered Two");
    }


}
