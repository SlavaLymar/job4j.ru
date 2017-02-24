package ru.lymar.dip.context;

import ru.lymar.dip.food.Food;
import ru.lymar.dip.store.*;
import ru.lymar.dip.storedecorator.StoreDecorator;

import java.util.ArrayList;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public class ExtendedControlQuality extends ControlQuality {

    private ExtendedWareHouse extendedWareHouse;
    private WareHouse warehouse;
    private Trash trash;

    public ExtendedControlQuality(int numberOfStrategy) {
        super(numberOfStrategy);
    }

    /**
     * getters
     */
    public ExtendedWareHouse getExtendedWareHouse() {
        return this.extendedWareHouse;
    }

    public WareHouse getWarehouse() {
        return this.warehouse;
    }

    public Trash getTrash() {
        return this.trash;
    }

    /**
     * fill new store
     */
    @Override
    public void fillStore(){

        this.warehouse = new WareHouse();
        this.extendedWareHouse = new ExtendedWareHouse(this.warehouse);
        this.trash = new Trash();

        super.store = new ArrayList<>();
        super.store.add(new Shop());
        super.store.add(new Refrigerator(this.extendedWareHouse));
        super.store.add(new ReproductStore(this.trash));
    }

    /**
     * select strategy
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
