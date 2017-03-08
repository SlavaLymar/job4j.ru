package ru.yalymar.generic.store;

/**
 * @author slavalymar
 * @since 06.03.2017
 * @version 1
 */
public class RoleStore <T extends Base> extends AbstractStore <T>{

    /**
     * class that contains stores
     */
    private SimpleArray<T> values;

    public RoleStore(int size) {
        this.values = new SimpleArray<>(size);
        super.values = this.values;
    }
}
