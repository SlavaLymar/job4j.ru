package ru.yalymar.configuration.model.crudrepo;

import org.hibernate.Session;
import java.util.function.Function;

/**
 * @author slavalymar
 * @since 13.06.2017
 * @version 1
 */
public interface Action<V> {

    <V, K> V tx(Function<Session, V> function);
}
