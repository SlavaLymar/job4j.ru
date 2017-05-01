package ru.yalymar.jdbc.model;

import java.sql.PreparedStatement;

public interface GoUpdate {

    int goUpdate(PreparedStatement st);
}
