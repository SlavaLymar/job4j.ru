package ru.lymar.foodstore.context;

import ru.lymar.foodstore.store.ExtendedWarHouse;
import ru.lymar.foodstore.store.Shop;
import ru.lymar.foodstore.store.Trash;
import ru.lymar.foodstore.store.WareHouse;
import java.util.ArrayList;

public class ExtendedControlQuality extends ControlQuality {

    public ExtendedControlQuality(int numberOfStrategy) {
        super(numberOfStrategy);
    }

    @Override
    public void fillStore(){
        super.store = new ArrayList<>();
        super.store.add(new Shop());
        super.store.add(new ExtendedWarHouse(new WareHouse()));
        super.store.add(new Trash());
    }
}
