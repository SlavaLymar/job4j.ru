package ru.yalymar.configuration.model.crudrepo;

public interface Read<E> {

    E read(int id);
}
