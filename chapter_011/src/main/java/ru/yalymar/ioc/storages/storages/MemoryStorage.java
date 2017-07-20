package ru.yalymar.ioc.storages.storages;

import ru.yalymar.ioc.storages.models.User;
import ru.yalymar.ioc.storages.storages.interfaces.Storage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @since 20.07.2017
 * @version 1
 */
public class MemoryStorage implements Storage {

    private List<User> list = new ArrayList<>();

    @Override
    public int add(User user) {
        return this.list.add(user) ? this.list.indexOf(user) : -1;
    }

    @Override
    public User get(int id) {
        User result = null;
        for (User u: this.list) {
            if(u.getId() == id){
                result = u;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean update(int id, User newUser) {
        boolean result = false;
        User user = this.list.get(id);
        if(!newUser.getLogin().equals(user.getLogin())){
            this.list.get(id).setLogin(newUser.getLogin());
            result = true;
        }
        if(!newUser.getPassword().equals(user.getPassword())){
            this.list.get(id).setPassword(newUser.getPassword());
            result = true;
        }
        if(!newUser.getRole().equals(user.getRole())){
            this.list.get(id).setRole(newUser.getRole());
            result = true;
        }
        return result;
    }

    @Override
    public void delete(int id) {
        this.list.remove(id);
    }

    @Override
    public List<User> getAll() {
        return this.list;
    }
}
