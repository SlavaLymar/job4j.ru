package ru.lymar.foodstore.context;

import ru.lymar.foodstore.food.Food;
import ru.lymar.foodstore.store.*;
import java.util.ArrayList;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public class ExtendedControlQuality extends ControlQuality {

    private ExtendedWarHouse extendedWarHouse;
    private WareHouse warhause;
    private Trash trash;

    /**
     * getters
     */
    public ExtendedControlQuality(int numberOfStrategy) {
        super(numberOfStrategy);
    }

    public ExtendedWarHouse getExtendedWarHouse() {
        return this.extendedWarHouse;
    }

    public WareHouse getWarhause() {
        return this.warhause;
    }

    public Trash getTrash() {
        return this.trash;
    }

    /**
     * fill new store
     */
    @Override
    public void fillStore(){

        this.warhause = new WareHouse();
        this.extendedWarHouse = new ExtendedWarHouse(this.warhause);
        this.trash = new Trash();

        super.store = new ArrayList<>();
        super.store.add(new Shop());
        super.store.add(new Refrigerator(this.extendedWarHouse));
        super.store.add(new ReproductStore(this.trash));
    }

    /**
     * select strategy
     */
    @Override
    public void selectStrategy(Food food){
        for(Store strategy : super.store){
            if (strategy.add(food)){
                break;
            }
        }
    }


}
