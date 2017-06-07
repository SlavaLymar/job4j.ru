package ru.yalymar.configuration.model.crudrepo;

public interface Update<E> {

    int update(int id, E e);
}
