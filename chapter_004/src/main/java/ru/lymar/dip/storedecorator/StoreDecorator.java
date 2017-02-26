package ru.lymar.dip.storedecorator;

import ru.lymar.dip.food.Food;
import ru.lymar.dip.store.GetBottomList;
import ru.lymar.dip.store.Store;
import java.util.ArrayList;
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
        List <Food> bottomList = new ArrayList<>();
        List <Food> bottomList1 = new ArrayList<>();
        if(this.isStoreDecorator(this.store)){
            bottomList1 = ((StoreDecorator) this.store).getStore().getList();
        }
        List <Food> bottomList2 = this.store.getList();

        if(bottomList1.size() > 0 && bottomList2.size() == 0){
            bottomList = bottomList1;
            return bottomList;
        }
        if(bottomList2.size() > 0 && bottomList1.size() == 0){
            bottomList = bottomList2;
            return bottomList;
        }
        if (bottomList1.size() > 0 && bottomList2.size() > 0){
            List <Food> tmp = bottomList1;
            for (Food food : bottomList2){
                tmp.add(food);
            }
            bottomList = tmp;
            return bottomList;
        }

        return bottomList;
    }

    /** Is Store StoreDecorator
     * @param store
     * @return boolean
     */
    private boolean isStoreDecorator(Store store){
        boolean result = false;
        try{
            StoreDecorator sd = (StoreDecorator) store;
            result = true;
        }
        catch (ClassCastException e){
            System.out.println("Impossible cast");
        }
        return result;
    }

    /** combine current and bottom Lists
     * @return List
     */
    @Override
    public List<Food> combineLists() {
        List <Food> result = new ArrayList<>();
        if(this.foodStore.size() > 0 && this.getBottomList().size() == 0){
            result = this.foodStore;
            return result;
        }
        if(this.getBottomList().size() > 0 && this.foodStore.size() == 0){
            result = this.getBottomList();
            return result;
        }
        if (this.getBottomList().size() > 0 && this.foodStore.size() > 0){
            List <Food> tmp = this.getBottomList();
            for (Food food : this.foodStore){
                tmp.add(food);
            }
            result = tmp;
            return result;
        }
        return result;
    }

    /** get bottom store
     * @return Store
     */
    public abstract Store getStore();
}
