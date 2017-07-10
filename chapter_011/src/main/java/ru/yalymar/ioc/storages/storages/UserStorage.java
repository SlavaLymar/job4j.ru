package ru.yalymar.ioc.storages.storages;

import ru.yalymar.ioc.storages.models.User;
import ru.yalymar.ioc.storages.storages.interfaces.Storage;

public class UserStorage {

    private final Storage storage;

    public UserStorage(final Storage storage) {
        this.storage = storage;
    }

    public int add(User user){
        return this.storage.add(user);
    }

    public User get(int id) {
        return (User) this.storage.get(id);
    }

    public boolean update(User user, User newUser) {
        return this.storage.update(user, newUser);
    }

    public void delete(int id) {
       this.storage.delete(id);
    }
}
