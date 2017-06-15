package ru.yalymar.mapping.model.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.yalymar.mapping.model.Role;
import ru.yalymar.mapping.model.User;
import ru.yalymar.mapping.model.unproxy.Unproxy;
import java.util.List;

public class UserDAO extends DAO<User> implements Unproxy<Role> {

    public int create(User user) {
        return super.tx(session -> (int) session.save(user));
    }

    public User read(int id) {
        User user = super.tx(session -> {
            User u = session.get(User.class, id);
            Role role = this.initializeAndUnproxy(u.getRole());
            u.setRole(role);
            return u;
        });
        return user;
    }

    public List<User> readAll() {
        List<User> users = super.tx(session -> {
            List<User> us = session.createQuery("from User").list();
            for (User user : us) {
                Role role = this.initializeAndUnproxy(user.getRole());
                user.setRole(role);
            }
            return us;
        });
        return users;
    }

    public int update(int id, User newUser) {
        int i = 0;
        User user = this.read(id);
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
        if(i > 0){
            super.tx(session -> {
                Query query = session.createQuery("update User set login = :l, " +
                        "password = :p, name = :n, role = :r where id = :id");
                query.setParameter("l", user.getLogin());
                query.setParameter("p", user.getPassword());
                query.setParameter("n", user.getName());
                query.setParameter("r", user.getRole());
                query.setParameter("id", id);
                return query.executeUpdate();
            });
        }
        return i;
    }

    public int delete(int id) {
        return super.tx(session -> {
            Query query = session.createQuery("delete User where id = :id");
            query.setParameter("id", id);
            return query.executeUpdate();
        });
    }

    public boolean isValid(String login, String password) {
        if(this.getByLoginPassword(login, password) != null){
            return true;
        }
        return false;
    }

    public boolean isAdmin(String login, String password){
        if("admin".equals(this.getByLoginPassword(login, password).getRole().getRole())){
            return true;
        }
        return false;
    }

    public User getByLoginPassword(String login, String password){
        User user = super.tx(session -> {
            Query<User> query = session.createQuery("from User where login = :l and password = :p");
            query.setParameter("l", login);
            query.setParameter("p", password);
            List<User> users = query.list();
            Role role = this.initializeAndUnproxy(users.get(0).getRole());
            users.get(0).setRole(role);
            return users.size() == 1 ? users.get(0) : null;
        });
        return user;
    }

}
