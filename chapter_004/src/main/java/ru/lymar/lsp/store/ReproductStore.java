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
        if (food.getCorruptionPercent() >= 100 && this.checkReproduct(food)) {
            this.reproductStore.add(food);
            return true;
        }
        else {
            this.store.add(food);
            return true;
        }
    }

    /** check that food is reproduct
     * @param food
     * @return boolean
     */
    public boolean checkReproduct(Food food){
        boolean result = false;
        try {
            ReproductFood reproductFood = (ReproductFood) food;
            result = true;
        }
        catch (ClassCastException e){
            System.out.println("Impossible cast");
        }
        return result;
    }

    @Override
    public List<Food> getList() {
        return this.reproductStore;
    }
}
