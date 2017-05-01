package ru.yalymar.jdbc.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface Go {

    ResultSet go(PreparedStatement st);
}
