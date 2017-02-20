package ru.lymar.foodstore.store;

import ru.lymar.foodstore.food.Food;
import ru.lymar.foodstore.food.ReproductFood;
import ru.lymar.foodstore.food.Vegetables;
import ru.lymar.foodstore.storedecorator.StoreDecorator;
import java.util.ArrayList;
import java.util.List;

public class Refrigerator extends StoreDecorator{

    private List <Food> refrigerator = new ArrayList<>();
    private Store store;

    public Refrigerator(Store store) {
        super(store);
        this.store = store;
    }

    @Override
    public void add(Food food) {

        try {
            Vegetables vegetables = (Vegetables) food;
            if (vegetables.isVegetables()) {
                this.refrigerator.add(food);
            }
        }
        catch (ClassCastException e){
            System.out.println("Impossible cast");
        }
    }

    @Override
    public List<Food> getList() {
        return this.refrigerator;
    }
}
