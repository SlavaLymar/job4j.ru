package ru.lymar.isp.action;

/**
 * @author slavalymar
 * @since 22.02.2017
 * @version 1
 */
public abstract class BaseActionLevel1 extends BaseAction{

    private String name;

    /**
     * @param name
     */
    public BaseActionLevel1(String name) {
        super(name);
        this.name = name;
    }

    /**
     * @return String
     */
    @Override
    public String print() {
        return String.format("%s %s", this.key(), this.name);
    }
}
