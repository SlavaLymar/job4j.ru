package ru.yalymar.filemanager.action;

public abstract class BaseAction implements ClientAction{

    private String name;

    public BaseAction(String name) {

        this.name = name;
    }

    @Override
    public String print(){
        return String.format("%s. %s", this.key()+1, this.name);
    }
}
