package ru.yalymar.ioc.storages.storages;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.yalymar.ioc.storages.models.User;
import ru.yalymar.ioc.storages.storages.interfaces.Storage;

import java.util.List;

/**
 * @author slavalymar
 * @since 20.07.2017
 * @version 1
 */
@Transactional
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
    public boolean update(int id, User newUser) {
        boolean result = false;
        User user = this.get(id);
        if(!newUser.getLogin().equals(user.getLogin())){
            user.setLogin(newUser.getLogin());
            result = true;
        }
        if(!newUser.getPassword().equals(user.getPassword())){
            user.setPassword(newUser.getPassword());
            result = true;
        }
        if(!newUser.getRole().equals(user.getRole())){
            user.setRole(newUser.getRole());
            result = true;
        }
        this.hibernateTemplate.update(user);
        return result;
    }

    @Override
    public void delete(int id) {
        User user = this.get(id);
        this.hibernateTemplate.delete(user);
    }

    @Override
    public List<User> getAll() {
        List<User> result = this.hibernateTemplate.loadAll(User.class);
        return result;
    }
}
