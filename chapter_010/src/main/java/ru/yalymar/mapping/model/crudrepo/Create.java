package ru.yalymar.mapping.model.crudrepo;

public interface Create<E> {

    int daoCreate(E e);
}
