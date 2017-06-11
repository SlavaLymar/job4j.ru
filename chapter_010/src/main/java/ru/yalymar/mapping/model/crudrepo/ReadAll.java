package ru.yalymar.mapping.model.crudrepo;

import java.util.List;

public interface ReadAll<E> {

    List<E> daoReadAll();
}
