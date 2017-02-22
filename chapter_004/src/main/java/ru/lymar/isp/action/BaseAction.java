package ru.lymar.isp.action;

/**
 * @author slavalymar
 * @since 22.02.2017
 * @version 1
 */
public abstract class BaseAction implements UserAction{

    private String name;

    /**
     * @param name
     */
    public BaseAction(String name) {
        this.name = name;
    }
}
