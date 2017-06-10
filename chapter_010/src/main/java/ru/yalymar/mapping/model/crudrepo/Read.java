package ru.yalymar.mapping.model.crudrepo;

public interface Read<E> {

    E read(int id);
}
