package ru.yalymar.mapping.model.manager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.yalymar.mapping.model.Role;
import ru.yalymar.mapping.model.User;
import java.util.List;

public class UserManager extends Manager<User> implements Unproxy<Role>{

    @Override
    public int create(User item) {
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
    public User read(int id) {
        Session session = null;
        try {
            session = super.sessionFactory.openSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            Role role = this.initializeAndUnproxy(user.getRole());
            user.setRole(role);
            return user;
        }
        finally {
            session.getTransaction().commit();
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public List<User> readAll() {
        Session session = null;
        try{
            session = super.sessionFactory.openSession();
            return session.createQuery("from User").list();
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public int update(int id, User newUser) {
        Session session = null;
        int i = 0;
        try{
            session = super.sessionFactory.openSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            user.setLogin(newUser.getLogin());
            user.setPassword(newUser.getPassword());
            user.setName(newUser.getName());
            user.setRole(newUser.getRole());
            session.update(user);
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
    public int delete(int id) {
        Session session = null;
        int i = -1;
        try {
            session = super.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery(
                    "delete User where id = :i");
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
