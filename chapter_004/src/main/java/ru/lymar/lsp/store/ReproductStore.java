package ru.lymar.lsp.store;

import ru.lymar.lsp.food.ReproductFood;
import ru.lymar.lsp.storedecorator.Reproduct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public class ReproductStore implements Reproduct {

    private List <ReproductFood> reproductStore = new ArrayList<>();

    /** if food is reproduct add to reproductStore
     * @param food
     * @return boolean
     */
    @Override
    public boolean add(ReproductFood food) {
        if (food.getCorruptionPercent() >= 100) {
            this.reproductStore.add(food);
            return true;
        }
        return false;
    }

    /** getter
     * @return List
     */
    @Override
    public List<ReproductFood> getList() {
        return this.reproductStore;
    }
}