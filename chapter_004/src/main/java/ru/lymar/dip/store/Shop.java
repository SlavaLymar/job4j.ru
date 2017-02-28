package ru.lymar.dip.store;

import ru.lymar.dip.food.Food;
import ru.lymar.dip.food.Vegetables;
import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public class Shop implements Store {

    /**
     * list of foods
     */
    private List<Food> shop = new ArrayList<>();

    /** add a food to list
     * @param food
     */
    @Override
    public boolean add(Food food) {
        if(food.getCorruptionPercent() >= 25 && food.getCorruptionPercent() < 75){
            this.shop.add(food);
            return true;
        }
        if(food.getCorruptionPercent() >= 75 && food.getCorruptionPercent() < 100){
            food.setPrice(food.getPrice()*(Integer.valueOf(food.getDiscountPercent())/100));
            this.shop.add(food);
            return true;
        }
        return false;
    }

    /**
     * getter
     */
    public List<Food> getList() {

        return this.shop;
    }

}
