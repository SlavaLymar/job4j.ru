package ru.yalymar.filemanager.action;

/**
 * @author slavalymar
 * @since 05.02.2017
 * @version 1
 */
public abstract class BaseAction implements ClientAction{

    private String name;

    public BaseAction(String name) {
        this.name = name;
    }

    /** print help
     * @return
     */
    @Override
    public String print(){
        return String.format("%s. %s", this.key()+1, this.name);
    }
}
