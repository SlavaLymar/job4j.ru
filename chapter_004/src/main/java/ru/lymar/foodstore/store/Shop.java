package ru.lymar.foodstore.store;

import ru.lymar.foodstore.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {

    private List<Food> shop = new ArrayList<>();

    @Override
    public void add(Food food) {
        if(food.getFreshnessPercent() >= 25 && food.getFreshnessPercent() <= 75){
            this.shop.add(food);
        }
    }
}
