package ru.yalymar.configuration.model.crudrepo;

public interface Create<E> {

    int daoCreate(E e);
}
