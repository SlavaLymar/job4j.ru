package ru.yalymar.mapping.model.crudrepo;

public interface Update<E> {

    int update(int id, E e);
}
