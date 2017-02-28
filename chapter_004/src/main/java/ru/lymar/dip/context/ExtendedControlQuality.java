package ru.lymar.dip.context;

import ru.lymar.dip.food.Food;
import ru.lymar.dip.food.ReproductFood;
import ru.lymar.dip.food.Vegetables;
import ru.lymar.dip.store.*;
import ru.lymar.dip.storedecorator.RefrigeratorStore;
import ru.lymar.dip.storedecorator.Reproduct;
import ru.lymar.dip.storedecorator.StoreDecorator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public class ExtendedControlQuality extends ControlQuality {

    private ExtendedWareHouse extendedWareHouse;
    private WareHouse wareHouse;

    private List <Reproduct> reproductStore = new ArrayList<>();
    private List <Refrigerator> refrigeratorStore = new ArrayList<>();

    public ExtendedControlQuality(int numberOfStrategy) {
        super(numberOfStrategy);
    }

    /**
     * getters
     */
    public ExtendedWareHouse getExtendedWareHouse() {
        return this.extendedWareHouse;
    }

    public WareHouse getWareHouse() {
        return this.wareHouse;
    }

    public List<Reproduct> getReproductStore() {
        return reproductStore;
    }

    public List<Refrigerator> getRefrigeratorStore() {
        return refrigeratorStore;
    }

    /**
     * fill new store
     */
    @Override
    public void fillStore(){

        this.wareHouse = new WareHouse();
        this.extendedWareHouse = new ExtendedWareHouse(this.wareHouse);

        super.store = new ArrayList<>();
        super.store.add(new Shop());
        super.store.add(this.extendedWareHouse);
        super.store.add(new Trash());

        this.refrigeratorStore.add(new Refrigerator());
        this.reproductStore.add(new ReproductStore());
    }

    /**
     * select strategy for food
     */
    @Override
    public void selectStrategy(Food food){
        for(Store strategy : super.store){
            if (strategy.add(food)){
                break;
            }
        }
    }


    /**
     * select strategy for reproduct food
     */
    public void selectStrategy(ReproductFood food){
        for(Reproduct strategy: this.reproductStore){
            if(strategy.add(food)){
                break;
            }
        }
    }

    /**
     * select strategy for vegetables
     */
    public void selectStrategy(Vegetables food){
        for(RefrigeratorStore strategy: this.refrigeratorStore){
            if(strategy.add(food)){
                break;
            }
        }
    }

    /**
     * resort foods
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
        for(Store s : super.store){
            if(this.isStoreDecorator(s)) {
                StoreDecorator sd = (StoreDecorator) s;
                for (Food food : sd.combineLists()) {
                    super.allFoods.add(food);
                }
            }
            else {
                for (Food food : s.getList()){
                    super.allFoods.add(food);
                }
            }
            super.deleteList(s);
        }
    }

    /** Is Store StoreDecorator
     * @param store
     * @return boolean
     */
    private boolean isStoreDecorator(Store store){
        boolean result = false;
        try{
            StoreDecorator sd = (StoreDecorator) store;
            result = true;
        }
        catch (ClassCastException e){
            System.out.println("Impossible cast");
        }
        return result;
    }
}
