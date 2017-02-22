package ru.lymar.lsp.storedecorator;

import ru.lymar.lsp.store.Store;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public abstract class StoreDecorator implements Store {

    private Store store;

    public StoreDecorator(Store store) {
        this.store = store;
    }

}
