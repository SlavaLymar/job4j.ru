package ru.yalymar.mvc.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.yalymar.mvc.model.dao.action.Action;

import java.util.function.Function;

@Transactional
public abstract class DAO<E> implements Action<E> {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public <V, K> V action(Function<HibernateTemplate, V> function) {
        return function.apply(hibernateTemplate);
    }

}
