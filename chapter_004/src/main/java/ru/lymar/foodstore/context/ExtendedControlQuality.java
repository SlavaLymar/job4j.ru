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

    public ExtendedControlQuality(int numberOfStrategy) {
        super(numberOfStrategy);
    }

    /**
     * fill new store
     */
    @Override
    public void fillStore(){
        super.store = new ArrayList<>();
        super.store.add(new Shop());
        super.store.add(new Refrigerator(new ExtendedWarHouse(new WareHouse())));
        super.store.add(new ReproductStore(new Trash()));
    }

    /**
     * select strategy
     */
    @Override
    public void selectStrategy(Food food){
        for(Store strategy : this.store){
            if (strategy.add(food)){
                break;
            }
        }
    }


}
