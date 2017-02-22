package ru.lymar.lsp.store;

import ru.lymar.lsp.food.Food;
import ru.lymar.lsp.food.ReproductFood;
import ru.lymar.lsp.storedecorator.StoreDecorator;
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
    public boolean add(Food food) {
        try {
            ReproductFood reproductFood = (ReproductFood) food;
            if (food.getCorruptionPercent() >= 100 && reproductFood.isCanReproduct()) {
                this.reproductStore.add(food);
                return true;
            }
        }
        catch (ClassCastException e){
            System.out.println("Impossible cast");
            this.store.add(food);
            return true;
        }
        return false;
    }

    @Override
    public List<Food> getList() {
        return this.reproductStore;
    }
}
