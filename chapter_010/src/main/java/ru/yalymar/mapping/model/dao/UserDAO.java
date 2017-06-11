package ru.yalymar.mapping.model.dao;

import org.hibernate.Session;
import ru.yalymar.mapping.model.Role;
import ru.yalymar.mapping.model.User;
import ru.yalymar.mapping.model.unproxy.Unproxy;
import java.util.List;

public class UserDAO extends DAO<User> implements Unproxy<Role> {

    public int create(User user) {
        int i = super.create.daoCreate(user);
        int id = -1;
        if (i > 0) {
            id = user.getId();
        }
        return id;
    }

    @Override
    public User daoRead(int id) {
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
    public List<User> daoReadAll() {
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

    public int update(int id, User newUser) {
        int i = 0;
        User user = this.daoRead(id);
        if (newUser.getLogin() != null) {
            user.setLogin(newUser.getLogin());
            i++;
        }
        if (newUser.getPassword() != null) {
            user.setPassword(newUser.getPassword());
            i++;
        }
        if (newUser.getName() != null) {
            user.setName(newUser.getName());
            i++;
        }
        if (newUser.getRole() != null) {
            user.setRole(newUser.getRole());
            i++;
        }
        super.update.daoUpdate(user);
        return i;
    }

    public int delete(int id) {
        String query = String.format("daoDelete User where id = %d", id);
        int i = super.delete.daoDelete(query);
        return i;
    }
}
