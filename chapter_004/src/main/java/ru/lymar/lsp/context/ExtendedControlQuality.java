package ru.lymar.lsp.context;

import ru.lymar.lsp.food.Food;
import ru.lymar.lsp.food.ReproductFood;
import ru.lymar.lsp.food.Vegetables;
import ru.lymar.lsp.store.*;
import ru.lymar.lsp.storedecorator.RefrigeratorStore;
import ru.lymar.lsp.storedecorator.Reproduct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public class ExtendedControlQuality extends ControlQuality {

    private ExtendedWarHouse extendedWareHouse;
    private WareHouse wareHouse;

    private List <Reproduct> reproductStore = new ArrayList<>();
    private List <Refrigerator> refrigeratorStore = new ArrayList<>();

    public ExtendedControlQuality(int numberOfStrategy) {
        super(numberOfStrategy);
    }

    /**
     * getters
     */
    public ExtendedWarHouse getExtendedWareHouse() {
        return this.extendedWareHouse;
    }

    public WareHouse getWareHouse() {
        return this.wareHouse;
    }

    public List<Reproduct> getReproductStore() {
        return this.reproductStore;
    }

    public List<Refrigerator> getRefrigeratorStore() {
        return this.refrigeratorStore;
    }

    /**
     * fill new store
     */
    @Override
    public void fillStore(){

        this.wareHouse = new WareHouse();
        this.extendedWareHouse = new ExtendedWarHouse(this.wareHouse);

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

}
