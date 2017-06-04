package ru.yalymar.testtask.model.dao;

import java.util.List;

/**
 * @author slavalymar
 * @version 1
 * @since 04.06.2017
 */
public interface DAO<E> {

    /** create model
     * @param e
     * @return int
     */
    int create(E e);

    /** get all models
     * @return List
     */
    List<E> getAll();

    /** get model by ID
     * @param id
     * @return E
     */
    E getById(int id);

    /** edit model
     * @param id
     * @param e
     * @return int
     */
    int edit(int id, E e);

    /** remove model
     * @param id
     * @return int
     */
    int remove(int id);
}
