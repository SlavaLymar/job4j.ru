package ru.lymar.foodstore.store;

import ru.lymar.foodstore.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {

    private List<Food> trash = new ArrayList<>();

    @Override
    public void add(Food food) {
        if(food.getFreshnessPercent() >= 100){
            this.trash.add(food);
        }
    }


}
