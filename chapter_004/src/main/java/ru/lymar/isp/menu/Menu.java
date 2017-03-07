package ru.lymar.isp.menu;

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
    private Item[] userActions = new Item[3];
    private boolean b = true;

    /**
     * create menu
     */
    public void fillMenu(){
        this.userActions[0] =
                new Item("1 Item", new Item("1.1 Item"),
                        new Item("1.2 Item", new Item("1.2.3 Item")));
        this.userActions[1] = new Item("2 Item");
        this.userActions[2] = new Exit("Exit", this);
    }

    public Item[] getUserActions() {
        return this.userActions;
    }

    /**
     * @param key
     */
    public void select(Item[] userActions, String key){
        for(Item item: userActions){
            if(item != null && key.equals(item.getKey())) {
                item.execute(this.input);
                break;
            }
            if(item.getItems().length > 0){
                this.select(item.getItems(), key);
            }
        }
    }

    /**
     * @return List <String>
     */
    public List <String> getKeysArr(Item[] userActions){
        List <String> keyList = new ArrayList<>();
        for(Item item: userActions){
            if(item != null) {
                keyList.add(item.getKey());
                if(item.getItems() != null){
                    this.getKeysArr(item.getItems());
                }
            }
        }
        return keyList;
    }

    /**
     * show menu
     */
    public void showMenu(Item[] userActions){
        for(Item item: userActions){
            if(item != null) {
                item.print();
                if(item.getItems() != null){
                    this.showMenu(item.getItems());
                }
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



}

