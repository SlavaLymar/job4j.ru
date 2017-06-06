package ru.yalymar.configuration.model.crudrepo;

import java.util.List;

public interface Read<E> {

    List<E> read(int id);
}
