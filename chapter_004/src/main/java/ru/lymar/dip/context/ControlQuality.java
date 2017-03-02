package ru.lymar.dip.context;

import ru.lymar.dip.food.Food;
import ru.lymar.dip.store.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public class ControlQuality implements ResortFood{

    /**
     * list of stores
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
     * @return list
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
     * fill list of stores
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

    /**
     * resort food
     */
    @Override
    public void resort() {
        for(Store store: this.store){
            List <Food> f = new CopyOnWriteArrayList<>(store.getList());
            for(Food food : f){
                for(Store newStore : this.store){
                    if(newStore.add(food)){
                        store.getList().remove(food);
                        break;
                    }
                }
            }
        }
    }

}
