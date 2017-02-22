package ru.lymar.lsp.store;

import ru.lymar.lsp.food.Food;
import ru.lymar.lsp.food.Vegetables;

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
        if(food.getCorruptionPercent() >= 25 && food.getCorruptionPercent() < 75 && !isVegetables(food)){
            this.shop.add(food);
            return true;
        }
        if(food.getCorruptionPercent() >= 75 && food.getCorruptionPercent() < 100 && !isVegetables(food)){
            food.setPrice(food.getPrice()*(Integer.valueOf(food.getDiscountPercent())/100));
            this.shop.add(food);
            return true;
        }
        return false;
    }

    /** check if food is vegetable
     * @param food
     * @return boolean
     */
    private boolean isVegetables(Food food){
        boolean flag = false;
        try{
            Vegetables vegetables = (Vegetables) food;
            flag = true;
        }
        catch (ClassCastException e){
            System.out.println("Impossible cast");
        }
        return flag;
    }

    /**
     * getter
     */
    public List<Food> getList() {

        return this.shop;
    }

}
