package ru.yalymar.configuration.model.crudrepo;

import java.util.List;

public interface ReadAll<E> {

    List<E> readAll();
}
