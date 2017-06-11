package ru.yalymar.configuration.model.crudrepo;

public interface Update<E> {

    void daoUpdate(E e);
}
