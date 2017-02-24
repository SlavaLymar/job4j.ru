package ru.lymar.dip.storedecorator;

import ru.lymar.dip.food.Food;
import ru.lymar.dip.store.GetBottomList;
import ru.lymar.dip.store.Store;
import java.util.List;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public abstract class StoreDecorator implements Store, GetBottomList{

    private Store store;
    protected List <Food> foodStore;

    public StoreDecorator(Store store) {
        this.store = store;
    }

    /** get bottom list of shop
     * @return List
     */
    @Override
    public List<Food> getBottomList() {
        List <Food> bottomList = this.store.getList();
        return bottomList;
    }

    /** combine current and bottom Lists
     * @return List
     */
    @Override
    public List<Food> combineLists() {
        List <Food> result = null;
        if(this.foodStore.size() > 0 && this.getBottomList().size() == 0){
            result = this.store.getList();
        }
        if(this.getBottomList().size() > 0 && this.foodStore.size() == 0){
            return this.getBottomList();
        }
        if (this.getBottomList().size() > 0 && this.foodStore.size() > 0){
            List <Food> tmp = this.getBottomList();
            for (Food food : this.foodStore){
                tmp.add(food);
            }
            result = tmp;
        }
        return result;
    }

}
