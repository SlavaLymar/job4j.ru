package ru.yalymar.testtask.model.dao;

import java.util.List;

public interface DAO<E> {

    int create(E e);

    List<E> getAll();

    E getById(int id);

    int edit(int id, E e);

    int remove(int id);
}
