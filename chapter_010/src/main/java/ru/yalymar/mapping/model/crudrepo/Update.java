package ru.yalymar.mapping.model.crudrepo;

public interface Update<E> {

    void daoUpdate(E e);
}
