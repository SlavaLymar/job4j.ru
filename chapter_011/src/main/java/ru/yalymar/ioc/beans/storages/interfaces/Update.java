package ru.yalymar.ioc.beans.storages.interfaces;

import ru.yalymar.ioc.beans.models.User;

public interface Update {

    boolean update(User user, User newUser);
}
