package ru.lymar.isp.menu;

import ru.lymar.isp.action.BaseAction;
import ru.lymar.isp.action.UserAction;
import ru.lymar.isp.input.Input;
import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @since 22.02.2017
 * @version 1
 */
public class Menu {

    private Input input;
    private List<UserAction> userActions = new ArrayList<>();
    private boolean b = true;

    /**
     * initialized
     */
    private void init(){

    }

    /**
     * create menu
     */
    public void fillMenu(){

        this.init();
        this.userActions.add(new Item("1 Menu", 1));
        this.userActions.add(new Item("1.1 Menu", 2));
        this.userActions.add(new Item("1.1.1 Menu", 3));
        this.userActions.add(new Item("2 Menu", 1));
        this.userActions.add(new Item("2.1 Menu", 2));

        this.userActions.add(new Exit("Exit"));
    }

    /**
     * @param key
     */
    public void select(String key){
        for(UserAction userAction : this.userActions){
            if(key.equals(userAction.getKey())){
                userAction.execute(this.input);
            }
        }
    }

    /**
     * @return List <String>
     */
    public List <String> getKeysArr(){
        List <String> keyList = new ArrayList<>();
        for(UserAction userAction : this.userActions){
            keyList.add(userAction.getKey());
        }
        return keyList;
    }

    /**
     * show menu
     */
    public void showMenu(){
        for(UserAction userAction: this.userActions){
            if(userAction!=null) {
                userAction.print();
            }
        }
    }

    public Menu(Input input) {
        this.input = input;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public boolean isB() {
        return b;
    }


    /**
     * @author slavalymar
     * @since 22.02.2017
     * @version 1
     */
    private class Exit extends BaseAction {

        private String name;

        public Exit(String name) {
            super(name);
            this.name = name;
        }

        /**
         * @param input
         */
        @Override
        public void execute(Input input) {
            setB(false);
        }


        /**
         * print
         */
        @Override
        public void print() {
            System.out.println(this.name);
        }
    }



}

