package ru.lymar.foodstore.store;

import ru.lymar.foodstore.food.Food;
import ru.lymar.foodstore.storedecorator.StoreDecorator;
import java.util.ArrayList;
import java.util.List;

public class ExtendedWarHouse extends StoreDecorator {

    private List<Food> extendedWarehouse = new ArrayList<>();

    private Store store;

    public ExtendedWarHouse(Store store) {
        super(store);
        this.store = store;
    }

    @Override
    public boolean add(Food food) {
        if(food.getCorruptionPercent() < 25 && this.store.getList().size() > 0){
            this.extendedWarehouse.add(food);
            return true;
        }
        else if(food.getCorruptionPercent() < 25 && this.store.getList().size() < 1){
            this.store.getList().add(food);
            return true;
        }
        return false;
    }

    @Override
    public List<Food> getList() {
        return this.extendedWarehouse;
    }

}
