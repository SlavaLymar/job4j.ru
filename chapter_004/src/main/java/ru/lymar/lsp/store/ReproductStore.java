package ru.lymar.lsp.store;

import ru.lymar.lsp.food.ReproductFood;
import ru.lymar.lsp.storedecorator.Reproduct;
import java.util.ArrayList;
import java.util.List;

public class ReproductStore implements Reproduct {

    private List <ReproductFood> reproductStore = new ArrayList<>();

    @Override
    public boolean add(ReproductFood food) {
        if (food.getCorruptionPercent() >= 100) {
            this.reproductStore.add(food);
            return true;
        }
        return false;
    }

    @Override
    public List<ReproductFood> getList() {
        return this.reproductStore;
    }
}