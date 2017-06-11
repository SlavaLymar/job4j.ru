package ru.yalymar.mapping.model.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.yalymar.mapping.model.Role;
import ru.yalymar.mapping.model.User;
import ru.yalymar.mapping.model.unproxy.Unproxy;
import java.util.List;

public class UserDAO extends DAO<User> implements Unproxy<Role> {

    @Override
    public int create(User user) {
        Session session = null;
        try {
            session = super.sessionFactory.openSession();
            session.beginTransaction();
            int i = (Integer) session.save(user);
            int id = -1;
            if(i > 0){
                id = user.getId();
            }
            session.getTransaction().commit();
            return id;
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
            User user = session.get(User.class, id);
            Role role = this.initializeAndUnproxy(user.getRole());
            user.setRole(role);
            return user;
        }
        finally {
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
            List<User> users = session.createQuery("from User").list();
            for(User user : users){
                Role role = this.initializeAndUnproxy(user.getRole());
                user.setRole(role);
            }
            return users;
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
            if(newUser.getLogin() != null) {
                user.setLogin(newUser.getLogin());
                i++;
            }
            if(newUser.getPassword() != null) {
                user.setPassword(newUser.getPassword());
                i++;
            }
            if(newUser.getName() != null){
                user.setName(newUser.getName());
                i++;
            }
            if(newUser.getRole() != null){
                user.setRole(newUser.getRole());
                i++;
            }
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
        int i;
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
