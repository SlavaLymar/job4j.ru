package ru.yalymar.ioc.storages.storages.interfaces;

import ru.yalymar.ioc.storages.models.User;

/**
 * @author slavalymar
 * @since 20.07.2017
 * @version 1
 */
public interface Update {

    boolean update(int id, User newUser);
}
