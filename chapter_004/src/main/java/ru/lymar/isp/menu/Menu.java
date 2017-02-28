package ru.lymar.isp.menu;

import ru.lymar.isp.action.BaseActionLevel1;
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
    private Node node;

    private One one;
        private OneOne oneOne;
        private OneTwo oneTwo;
            private OneTwoOne oneTwoOne;
    private Two two;
        private TwoOne twoOne;
        private TwoTwo twoTwo;
        private TwoThree twoThree;

    /**
     * initialized
     */
    private void init(){
        this.one = new One("One");
            this.oneOne = new OneOne("OneOne");
            this.oneTwo = new OneTwo("OneTwo");
                this.oneTwoOne = new OneTwoOne("OneTwoOne");
        this.two = new Two("Two");
            this.twoOne = new TwoOne("TwoOne");
            this.twoTwo = new TwoTwo("TwoTwo");
            this.twoThree = new TwoThree("TwoThree");
    }

    /**
     * create menu
     */
    public void fillMenu(){

        this.init();
        this.userActions.add(this.one);
            this.userActions.add(this.oneOne);
            this.userActions.add(this.oneTwo);
                this.userActions.add(this.oneTwoOne);
        this.userActions.add(this.two);
            this.userActions.add(this.twoOne);
            this.userActions.add(this.twoTwo);
            this.userActions.add(this.twoThree);

        this.userActions.add(new Exit("Exit"));
    }

    /**
     * @param key
     */
    public void select(String key){
        for(UserAction ua : this.userActions){
            if(key.equals(ua.key())){
                ua.execute(this.input);
            }
        }
    }

    /**
     * @return List <String>
     */
    public List <String> getKeysArr(){
        List <String> list = new ArrayList<>();
        for(UserAction ua : this.userActions){
            list.add(ua.key());
        }
        return list;
    }

    /**
     * show menu
     */
    public void showMenu(){
        for(UserAction userAction: this.userActions){
            if(userAction!=null) {
                System.out.println(userAction.print());
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
    private class Exit extends BaseActionLevel1 {

        private String name;

        public Exit(String name) {
            super(name);
            this.name = name;
        }

        @Override
        public String key() {
            final String k = "3";
            return k;
        }

        /**
         * @param input
         */
        @Override
        public void execute(Input input) {
            setB(false);
        }
    }



}

