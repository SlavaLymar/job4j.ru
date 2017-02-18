package ru.lymar.foodstore.store;

import ru.lymar.foodstore.food.Food;

import java.util.ArrayList;
import java.util.List;

public class WareHouse implements Store {

    private List<Food> warehouse = new ArrayList<>();

    @Override
    public void add(Food food) {
        if(food.getFreshnessPercent() < 25){
            this.warehouse.add(food);
        }
    }
}
