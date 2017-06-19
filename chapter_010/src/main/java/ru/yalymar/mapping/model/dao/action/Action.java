package ru.yalymar.mapping.model.dao.action;

import org.hibernate.Session;
import java.util.function.Function;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
public interface Action<V> {

    <V, K> V tx(Function<Session, V> function);
}
