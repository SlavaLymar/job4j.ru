package ru.yalymar.mapping.model.manager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.yalymar.mapping.model.Role;

import java.util.List;

public class RoleManager extends Manager<Role> {

    @Override
    public int create(Role item) {
        Session session = null;
        try {
            session = super.sessionFactory.openSession();
            session.beginTransaction();
            int i = (Integer) session.save(item);
            session.getTransaction().commit();
            return i;
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public Role read(int id) {
        Session session = null;
        try {
            session = super.sessionFactory.openSession();
            return session.get(Role.class, id);
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public List<Role> readAll() {
        Session session = null;
        try{
            session = super.sessionFactory.openSession();
            return session.createQuery("from Role").list();
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public int update(int id, Role newRole) {
        return -1;
    }

    @Override
    public int delete(int id) {
        Session session = null;
        int i = -1;
        try {
            session = super.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery(
                    "delete Role where id = :i");
            query.setParameter("i", id);
            i = query.executeUpdate();
            session.getTransaction().commit();
            return i;
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }
}
