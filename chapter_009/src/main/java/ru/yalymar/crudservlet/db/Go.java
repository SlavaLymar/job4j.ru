package ru.yalymar.crudservlet.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author slavalymar
 * @since 02.05.2017
 * @version 1
 */
public interface Go {

    /** select query
     * @param st
     * @return ResultSet
     */
    ResultSet go(PreparedStatement st);
}
