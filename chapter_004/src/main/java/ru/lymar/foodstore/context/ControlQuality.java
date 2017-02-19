package ru.lymar.foodstore.context;

import ru.lymar.foodstore.food.Food;
import ru.lymar.foodstore.store.*;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public class ControlQuality {


    private Food food;

    /**
     * Store arrays
     */
    private Store[] store;

    /**
     * Numerous of strategy
     */
    private final int NUMEROUSOFSTRATEGY = 3;

    public ControlQuality(Food food) {
        this.food = food;
        this.fillStore();
    }

    /** getter
     * @return Store[]
     */
    public Store[] getStore() {

        return this.store;
    }

    /**
     * fill Store array
     */
    public void fillStore(){
        this.store = new Store[NUMEROUSOFSTRATEGY];
        this.store[0] = new Shop();
        this.store[1] = new WareHouse();
        this.store[2] = new Trash();
    }

    /**
     * select strategy
     */
    public void selectStrategy(){
        for(Store strategy : this.store){
            strategy.add(this.food);
        }
    }
}
