package ru.lymar.lsp.store;

import ru.lymar.lsp.food.Vegetables;
import ru.lymar.lsp.storedecorator.RefrigeratorStore;
import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public class Refrigerator implements RefrigeratorStore{

    private List <Vegetables> refrigerator = new ArrayList<>();

    /** if food is vegetables add to refrigerator
     * @param food
     * @return boolean
     */
    @Override
    public boolean add(Vegetables food) {
        if (this.isVegetables(food)) {
            this.refrigerator.add(food);
            return true;
        }
        return false;
    }

    private boolean isVegetables(Vegetables food) {
        return true;
    }

    /**
     * getter
     */
    @Override
    public List<Vegetables> getList() {
        return this.refrigerator;
    }


}
