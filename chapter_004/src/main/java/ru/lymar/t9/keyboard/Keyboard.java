package ru.lymar.t9.keyboard;

import ru.lymar.t9.button.Button;
import ru.lymar.t9.button.Button1;
import ru.lymar.t9.button.Button2;
import ru.lymar.t9.button.Button3;

public class Keyboard {

    private final Button[] BUTTONS = new Button[3];

    public Button[] getBUTTONS() {
        return BUTTONS;
    }

    public void fillButtons(){
        this.BUTTONS[0] = new Button1("1", new char[]{'1', 'a', 'b', 'c'});
        this.BUTTONS[1] = new Button2("2", new char[]{'2', 'd', 'e', 'f'});
        this.BUTTONS[2] = new Button3("3", new char[]{'3', 'g', 'h', 'i'});
    }

}
