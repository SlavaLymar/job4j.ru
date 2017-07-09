package ru.yalymar.ioc.storages.storages;

import ru.yalymar.ioc.storages.models.User;

public interface Storage {

    int add(User user);

    User get(User user);

    boolean update(User user, User newUser);

    boolean delete(User user);
}
