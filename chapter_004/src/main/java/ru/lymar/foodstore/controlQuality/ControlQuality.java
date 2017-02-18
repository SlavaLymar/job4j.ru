package ru.lymar.foodstore.controlQuality;

import ru.lymar.foodstore.food.Food;
import ru.lymar.foodstore.store.*;

public class ControlQuality {

    private double freshnessPercent;
    private Food food;
    private Store[] store;

    public ControlQuality(Food food) {
        this.food = food;
        this.store = new Store[3];
        this.fillStore();
    }

    public void fillStore(){
        this.store[0] = new Shop();
        this.store[1] = new WareHouse();
        this.store[2] = new Trash();
    }

    public void selectStore(){
        for(Store s : store){
            s.add(this.food);
        }
    }
}
