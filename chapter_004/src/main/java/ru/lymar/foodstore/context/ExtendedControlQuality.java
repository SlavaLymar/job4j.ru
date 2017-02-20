package ru.lymar.foodstore.context;

import ru.lymar.foodstore.store.*;

import java.util.ArrayList;

public class ExtendedControlQuality extends ControlQuality {

    public ExtendedControlQuality(int numberOfStrategy) {
        super(numberOfStrategy);
    }

    @Override
    public void fillStore(){
        super.store = new ArrayList<>();
        super.store.add(new Shop());
        super.store.add(new Refrigerator(new ExtendedWarHouse(new WareHouse())));
        super.store.add(new ReproductStore(new Trash()));
    }
}
