package ru.yalymar.jdbc.start;

import ru.yalymar.jdbc.menu.Menu;
import ru.yalymar.jdbc.model.DBManager;
import ru.yalymar.jdbc.tracker.Tracker;

/**
 * @author slavalymar
 * @since 02.05.2017
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
       DBManager dbManager = new DBManager();
       dbManager.connectDB();
       Tracker tracker = new Tracker(dbManager);
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
