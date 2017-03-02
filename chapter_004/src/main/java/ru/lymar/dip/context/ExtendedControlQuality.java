package ru.lymar.dip.context;

import ru.lymar.dip.food.Food;
import ru.lymar.dip.food.ReproductFood;
import ru.lymar.dip.food.Vegetables;
import ru.lymar.dip.store.*;
import ru.lymar.dip.storedecorator.RefrigeratorStore;
import ru.lymar.dip.storedecorator.Reproduct;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public class ExtendedControlQuality extends ControlQuality {

    private ExtendedWareHouse extendedWareHouse;
    private WareHouse wareHouse;

    private List <Reproduct> reproductStore = new ArrayList<>();
    private List <Refrigerator> refrigeratorStore = new ArrayList<>();

    public ExtendedControlQuality(int numberOfStrategy) {
        super(numberOfStrategy);
    }

    /**
     * getters
     */
    public ExtendedWareHouse getExtendedWareHouse() {
        return this.extendedWareHouse;
    }

    public WareHouse getWareHouse() {
        return this.wareHouse;
    }

    public List<Reproduct> getReproductStore() {
        return reproductStore;
    }

    public List<Refrigerator> getRefrigeratorStore() {
        return refrigeratorStore;
    }

    /**
     * fill new store
     */
    @Override
    public void fillStore(){

        this.wareHouse = new WareHouse();
        this.extendedWareHouse = new ExtendedWareHouse(this.wareHouse);

        super.store = new ArrayList<>();
        super.store.add(new Shop());
        super.store.add(this.extendedWareHouse);
        super.store.add(new Trash());

        this.refrigeratorStore.add(new Refrigerator());
        this.reproductStore.add(new ReproductStore());
    }

    /**
     * select strategy for food
     */
    @Override
    public void selectStrategy(Food food){
        for(Store strategy : super.store){
            if (strategy.add(food)){
                break;
            }
        }
    }


    /**
     * select strategy for reproduct food
     */
    public void selectStrategy(ReproductFood food){
        for(Reproduct strategy: this.reproductStore){
            if(strategy.add(food)){
                break;
            }
        }
    }

    /**
     * select strategy for vegetables
     */
    public void selectStrategy(Vegetables food){
        for(RefrigeratorStore strategy: this.refrigeratorStore){
            if(strategy.add(food)){
                break;
            }
        }
    }

    /**
     * resort food
     */
    @Override
    public void resort() {
        super.resort();

        List <Food> f = new CopyOnWriteArrayList<>(this.wareHouse.getList());
        for(Food food : f){
            for(Store newStore : this.store){
                if(newStore.add(food)){
                    this.wareHouse.getList().remove(food);
                    break;
                }
            }
        }

    }
}
