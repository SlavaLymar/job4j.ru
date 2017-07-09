package ru.yalymar.ioc.storages.storages.interfaces;

import ru.yalymar.ioc.storages.models.User;

public interface Read {

    User get(User user);

}
