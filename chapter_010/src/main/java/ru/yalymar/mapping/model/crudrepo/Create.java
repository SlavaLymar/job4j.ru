package ru.yalymar.mapping.model.crudrepo;

import org.hibernate.Session;
import java.util.function.Function;

public interface Create<V> {

    <V, K> V tx(Function<Session, V> function);
}
