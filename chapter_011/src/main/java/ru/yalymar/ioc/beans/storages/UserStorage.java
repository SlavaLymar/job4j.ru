package ru.yalymar.ioc.beans.storages;

import ru.yalymar.ioc.beans.models.User;
import ru.yalymar.ioc.beans.storages.interfaces.Storage;

public class UserStorage {

    private final Storage storage;

    public UserStorage(final Storage storage) {
        this.storage = storage;
    }

    public void add(User user){
        this.storage.add(user);
    }
}
