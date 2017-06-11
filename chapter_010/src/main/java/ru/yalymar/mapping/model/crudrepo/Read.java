package ru.yalymar.mapping.model.crudrepo;

public interface Read<E> {

    E daoRead(int id);
}
