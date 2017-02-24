package ru.lymar.dip.context;

import ru.lymar.dip.food.Food;
import ru.lymar.dip.store.Shop;
import ru.lymar.dip.store.Store;
import ru.lymar.dip.store.Trash;
import ru.lymar.dip.store.WareHouse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public class ControlQuality {

    /**
     * Reproduct arrays
     */
    protected List<Store> store;

    /**
     * Numerous of strategy
     */
    private int numberOfStrategy;

    public ControlQuality(int numberOfStrategy) {
        this.numberOfStrategy = numberOfStrategy;
    }

    /** getter
     * @return Reproduct[]
     */
    public List<Store> getStore() {

        return this.store;
    }

    /**
     * setter
     */
    public void setStore(List<Store> store) {
        this.store = store;
    }

    /**
     * fill Reproduct array
     */
    public void fillStore(){
        this.store = new ArrayList<>();
        this.store.add(new Shop());
        this.store.add(new WareHouse());
        this.store.add(new Trash());
    }

    /**
     * select strategy
     */
    public void selectStrategy(Food food){
        for(Store strategy : this.store){
            strategy.add(food);
        }
    }

}
