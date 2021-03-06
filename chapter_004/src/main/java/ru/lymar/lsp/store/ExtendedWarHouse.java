package ru.lymar.lsp.store;

import ru.lymar.lsp.food.Food;
import ru.lymar.lsp.storedecorator.StoreDecorator;
import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public class ExtendedWarHouse extends StoreDecorator {

    private List<Food> extendedWarehouse = new ArrayList<>();

    private Store store;

    public ExtendedWarHouse(Store store) {
        super(store);
        this.store = store;
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
