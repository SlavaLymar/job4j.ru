package ru.yalymar.generic.store;

/**
 * @author slavalymar
 * @since 06.03.2017
 * @version 1
 */
public class UserStore extends AbstractStore<User>{

    /**
     * class that contains stores
     */
    private SimpleArray<User> values;

    public UserStore(int size) {
        this.values = new SimpleArray<>(size);
        super.values = this.values;
    }
}
