package ru.yalymar.ioc.storages.storages;

import org.springframework.orm.hibernate5.HibernateTemplate;
import ru.yalymar.ioc.storages.models.User;
import ru.yalymar.ioc.storages.storages.interfaces.Storage;

import java.util.List;

public class HibernateStorage implements Storage {

    private final HibernateTemplate hibernateTemplate;

    public HibernateStorage(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public int add(User user) {
        this.hibernateTemplate.save(user);
        return user.getId();
    }

    @Override
    public User get(int id) {
        return this.hibernateTemplate.get(User.class, id);
    }

    @Override
    public boolean update(User user, User newUser) {
        boolean result = false;
        if(!newUser.getLogin().equals(user.getLogin())){
            user.setLogin(newUser.getLogin());
        }
        if(!newUser.getPassword().equals(user.getPassword())){
            user.setPassword(newUser.getPassword());
        }
        if(!newUser.getRole().equals(user.getRole())){
            user.setRole(newUser.getRole());
        }
        this.hibernateTemplate.update(user);
        return result;
    }

    @Override
    public boolean delete(User user) {
        this.hibernateTemplate.delete(user);
        return true;
    }
}
