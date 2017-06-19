package ru.yalymar.mapping.model.dao.unproxy;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
public interface Unproxy<T> {

    default T initializeAndUnproxy(T entity) {
        if (entity == null) {
            throw new
                    NullPointerException("Entity passed for initialization is null");
        }

        Hibernate.initialize(entity);
        if (entity instanceof HibernateProxy) {
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
                    .getImplementation();
        }
        return entity;
    }
}
