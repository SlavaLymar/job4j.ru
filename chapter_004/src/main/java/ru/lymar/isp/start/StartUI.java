package ru.lymar.isp.start;

import ru.lymar.isp.input.Input;
import ru.lymar.isp.input.ValidateInput;
import ru.lymar.isp.menu.Exit;
import ru.lymar.isp.menu.Menu;

/**
 * @author slavalymar
 * @since 22.02.2017
 * @version 1
 */
public class StartUI {

    private Input input;

    public StartUI(Input input) {
        this.input = input;
    }
    private Menu menu;

    /**
     * start programm
     */
   public void init() {

       this.menu = new Menu(this.input);
       this.menu.fillMenu();
       this.mainLoop();

    }

    /**
     * main loop of app
     */
    public void mainLoop(){
        do{
            this.menu.showMenu(this.menu.getUserActions());
            this.menu.select(this.menu.getUserActions(), this.input.ask
                    ("Select a number of menu: ", menu.getKeysArr(this.menu.getUserActions())));
        }
        while(this.menu.isB());
    }

    /**
     * point of entry
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput()).init();
    }
}
