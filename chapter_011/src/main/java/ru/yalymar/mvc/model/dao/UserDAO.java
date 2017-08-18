package ru.yalymar.mvc.model.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import ru.yalymar.mvc.model.models.User;

import java.util.List;

@Service
public class UserDAO extends DAO<User>{

    public int create(User user) {
        return super.action(hibernateTemplate -> (int) hibernateTemplate.save(user));
    }

    public User read(int id) {
        User user = super.action(hibernateTemplate -> {
            User u = hibernateTemplate.get(User.class, id);
            return u;
        });
        return user;
    }

    public List<User> readAll() {
        return super.action(hibernateTemplate -> hibernateTemplate.loadAll(User.class));
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
            super.action(hibernateTemplate -> {
                hibernateTemplate.update(user);
                return -1;
            });
        }
        return i;
    }

    public int delete(int id) {
        User user = this.read(id);
        return super.action(hibernateTemplate -> {
            hibernateTemplate.delete(user);
            return 1;
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
        User user = super.action(hibernateTemplate -> {
            Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
            Query<User> query = session.createQuery("from User where login = :l and password = :p", User.class);
            query.setParameter("l", login);
            query.setParameter("p", password);
            List<User> users = query.list();
            return users.size() >= 1 ? users.get(0) : null;
        });
        return user;
    }

}
