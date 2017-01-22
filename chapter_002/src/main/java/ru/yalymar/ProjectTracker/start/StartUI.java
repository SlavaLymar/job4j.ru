package ru.yalymar.start;

import ru.yalymar.menu.Menu;
import ru.yalymar.tracker.Tracker;

/**
 * @author slavalymar
 * @since 13.01.2017
 * @version 1
 */
public class StartUI {

    private Input input;

    public StartUI(Input input) {
        this.input = input;
    }

    /**
     * start programm
     */
   public void init(){
       Tracker tracker = new Tracker();
       Menu menu = new Menu(this.input, tracker);
       menu.fillMenu();
       menu.greetings();

       do{
           menu.showMenu();
           menu.select(this.input.ask("Select a number of menu: ", menu.getIntArr()));
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
