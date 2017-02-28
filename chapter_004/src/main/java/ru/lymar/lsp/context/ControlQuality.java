package ru.lymar.lsp.context;

import ru.lymar.lsp.food.Food;
import ru.lymar.lsp.store.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public class ControlQuality {

    /**
     * NewTypeOfFood arrays
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
     * @return NewTypeOfFood[]
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
     * fill NewTypeOfFood array
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
