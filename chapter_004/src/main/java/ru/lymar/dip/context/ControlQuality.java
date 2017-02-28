package ru.lymar.dip.context;

import ru.lymar.dip.food.Food;
import ru.lymar.dip.store.*;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * list of all foods in Stores
     */
    protected List <Food> allFoods = new ArrayList<>();

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
        this.fillAllFoodsList();
        for(Food food: this.allFoods){
            this.selectStrategy(food);
        }
    }

    /**
     * fill list of all foods
     */
    private void fillAllFoodsList(){
        for(Store s : this.store){
            for (Food food: s.getList()){
                this.allFoods.add(food);
            }
            this.deleteList(s);
        }
    }

    /** remove the list of foods
     * @param store
     */
    protected void deleteList(Store store){
        store.getList().clear();
    }

}
