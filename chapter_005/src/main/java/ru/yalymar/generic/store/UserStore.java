package ru.yalymar.generic.store;

/**
 * @author slavalymar
 * @since 06.03.2017
 * @version 2
 */
public class UserStore extends AbstractStore<User>{

    public UserStore(int size) {
        super.values = new SimpleArray<>(size);
    }
}
