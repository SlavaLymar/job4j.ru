package ru.lymar.foodstore.storedecorator;

import ru.lymar.foodstore.store.Store;

public abstract class StoreDecorator implements Store {

    private Store store;

    public StoreDecorator(Store store) {
        this.store = store;
    }
}
