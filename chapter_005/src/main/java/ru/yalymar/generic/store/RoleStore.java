package ru.yalymar.generic.store;

/**
 * @author slavalymar
 * @since 06.03.2017
 * @version 1
 */
public class RoleStore extends AbstractStore <Role>{

    public RoleStore(int size) {
        super.values = new SimpleArray<>(size);
    }
}
