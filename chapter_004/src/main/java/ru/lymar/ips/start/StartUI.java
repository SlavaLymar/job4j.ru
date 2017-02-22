package ru.lymar.ips.start;

import ru.lymar.ips.input.Input;
import ru.lymar.ips.input.ValidateInput;
import ru.lymar.ips.menu.Menu;

public class StartUI {

    private Input input;

    public StartUI(Input input) {
        this.input = input;
    }

    /**
     * start programm
     */
   public void init() {

       Menu menu = new Menu(this.input);
       menu.fillMenu();

       do{
           menu.showMenu();
           menu.select(this.input.ask("Select a number of menu: ", menu.getKeysArr()));
       }
        while(menu.isB());
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
       new StartUI(new ValidateInput()).init();
    }
}
