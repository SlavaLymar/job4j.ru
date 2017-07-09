package ru.yalymar.ioc.storages.storages;

import ru.yalymar.ioc.storages.models.User;
import ru.yalymar.ioc.storages.storages.interfaces.Storage;

public class JdbcStorage implements Storage {

    @Override
    public int add(User user) {
        return 0;
    }

    @Override
    public User get(User user) {
        return null;
    }

    @Override
    public boolean update(User user, User newUser) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }
}
