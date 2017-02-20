package ru.lymar.foodstore.store;

import ru.lymar.foodstore.food.Food;
import ru.lymar.foodstore.food.ReproductFood;
import ru.lymar.foodstore.storedecorator.StoreDecorator;
import java.util.ArrayList;
import java.util.List;

public class ReproductStore extends StoreDecorator {

    private List <Food> reproductStore = new ArrayList<>();
    private Store store;

    public ReproductStore(Store store) {
        super(store);
        this.store = store;
    }

    @Override
    public void add(Food food) {
        try {
            ReproductFood reproductFood = (ReproductFood) food;
            if (food.getCorruptionPercent() >= 100 && reproductFood.isCanReproduct()) {
                this.reproductStore.add(food);
            } else {
                this.store.getList().add(food);
            }
        }
        catch (ClassCastException e){
            System.out.println("Impossible cast");
        }
    }

    @Override
    public List<Food> getList() {
        return this.reproductStore;
    }
}
