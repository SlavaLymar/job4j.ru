package ru.lymar.foodstore.store;

import ru.lymar.foodstore.food.Food;
import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public class Trash implements Store {

    /**
     * list of food
     */
    private List<Food> trash = new ArrayList<>();

    /** add a food to list
     * @param food
     */
    @Override
    public void add(Food food) {
        if(food.getCorruptionPercent() >= 100){
            this.trash.add(food);
        }
    }

    /**
     * getter
     */
    public List<Food> getList() {

        return this.trash;
    }

}
