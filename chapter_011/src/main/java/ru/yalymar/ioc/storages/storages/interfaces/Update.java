package ru.yalymar.ioc.storages.storages.interfaces;

import ru.yalymar.ioc.storages.models.User;

public interface Update {

    boolean update(int id, User newUser);
}
