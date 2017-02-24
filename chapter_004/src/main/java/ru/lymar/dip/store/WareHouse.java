package ru.lymar.dip.store;

import ru.lymar.dip.food.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public class WareHouse implements Store {

    /**
     * list of food
     */
    private List<Food> warehouse = new ArrayList<>();

    /** add a food to list
     * @param food
     */
    @Override
    public boolean add(Food food) {
        if(food.getCorruptionPercent() < 25){
            this.warehouse.add(food);
            return true;
        }
        return false;
    }

    /**
     * getter
     */
    public List<Food> getList() {

        return this.warehouse;
    }

}
