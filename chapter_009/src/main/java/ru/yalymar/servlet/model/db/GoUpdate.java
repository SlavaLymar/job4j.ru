package ru.yalymar.servlet.model.db;

import java.sql.PreparedStatement;

/**
 * @author slavalymar
 * @since 02.05.2017
 * @version 1
 */
public interface GoUpdate {

    /** update query
     * @param st
     * @return int
     */
    int goUpdate(PreparedStatement st);
}
