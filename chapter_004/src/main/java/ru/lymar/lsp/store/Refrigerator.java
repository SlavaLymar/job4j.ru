package ru.lymar.lsp.store;

import ru.lymar.lsp.food.Food;
import ru.lymar.lsp.food.Vegetables;
import ru.lymar.lsp.storedecorator.StoreDecorator;
import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public class Refrigerator extends StoreDecorator{

    private List <Food> refrigerator = new ArrayList<>();
    private Store store;

    public Refrigerator(Store store) {
        super(store);
        this.store = store;
    }

    /** if food is vegetables add to refrigerator
     * @param food
     * @return boolean
     */
    @Override
    public boolean add(Food food) {
        try {
            Vegetables vegetables = (Vegetables) food;
            if (vegetables.isVegetables()) {
                this.refrigerator.add(food);
                return true;
            }
        }
        catch (ClassCastException e){
            System.out.println("Impossible cast");
            this.store.add(food);
        }
        return false;
    }

    /**
     * getter
     */
    @Override
    public List<Food> getList() {
        return this.refrigerator;
    }

}
