package ru.lymar.dip.store;

import ru.lymar.dip.food.Food;
import ru.lymar.dip.storedecorator.StoreDecorator;
import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public class ExtendedWareHouse extends StoreDecorator{

    private List<Food> extendedWarehouse = new ArrayList<>();

    private Store store;

    public ExtendedWareHouse(Store store) {
        super(store);
        this.store = store;
        super.foodStore = this.extendedWarehouse;
    }

    @Override
    public Store getStore() {
        return this.store;
    }

    /** if main warehouse is full add to extended warehouse
     * @param food
     * @return boolean
     */
    @Override
    public boolean add(Food food) {
        if(food.getCorruptionPercent() < 25 && this.store.getList().size() > 0){
            this.extendedWarehouse.add(food);
            return true;
        }
        else {
            this.store.add(food);
            return false;
        }
    }

    /**
     * getter
     */
    @Override
    public List<Food> getList() {
        return this.extendedWarehouse;
    }

}
