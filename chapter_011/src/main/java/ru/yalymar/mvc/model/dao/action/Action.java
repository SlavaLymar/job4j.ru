package ru.yalymar.mvc.model.dao.action;

import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.function.Function;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
public interface Action<V> {

    <V, K> V action(Function<HibernateTemplate, V> function);
}
