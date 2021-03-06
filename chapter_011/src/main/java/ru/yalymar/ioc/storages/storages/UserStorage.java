package ru.yalymar.ioc.storages.storages;

import ru.yalymar.ioc.storages.models.User;
import ru.yalymar.ioc.storages.storages.interfaces.Storage;

import java.util.List;

/**
 * @author slavalymar
 * @since 20.07.2017
 * @version 1
 */
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

    public boolean update(int id, User newUser) {
        return this.storage.update(id, newUser);
    }

    public void delete(int id) {
       this.storage.delete(id);
    }

    public List<User> getAll(){
        return this.storage.getAll();
    }
}
