package ru.yalymar.action;

/**
 * @author slavalymar
 * @since 13.01.2017
 * @version 1
 */
public abstract class BaseAction implements UserAction{

    private String name;

    public BaseAction(String name) {
        this.name = name;
    }

    /**
     * @return String
     */
    @Override
    public String print(){
        return String.format("%s. %s", this.key()+1, this.name);
    }
}
