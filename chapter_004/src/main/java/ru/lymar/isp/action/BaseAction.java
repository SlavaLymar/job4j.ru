package ru.lymar.isp.action;

/**
 * @author slavalymar
 * @since 22.02.2017
 * @version 1
 */
public abstract class BaseAction implements UserAction{

    /**
     * name of item
     */
    private String name;

    /**
     * @param name
     */
    public BaseAction(String name) {
        this.name = name;
    }

    /** return number of menu
     * @return String
     */
    @Override
    public String getKey() {
        String[] s = this.name.split(" ");
        return s[0];
    }
}
