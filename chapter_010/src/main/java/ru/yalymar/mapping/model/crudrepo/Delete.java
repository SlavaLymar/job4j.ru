package ru.yalymar.mapping.model.crudrepo;

public interface Delete<E> {

    int daoDelete(String query);
}
