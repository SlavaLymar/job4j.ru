package ru.yalymar.ioc.storages.storages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yalymar.ioc.storages.models.User;

@Component
public class UserStorage {

    private final Storage storage;

    @Autowired
    public UserStorage(final Storage storage) {
        this.storage = storage;
    }

    public void add(User user){
        this.storage.add(user);
    }
}
