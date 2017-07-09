package ru.yalymar.ioc.beans.storages;

import ru.yalymar.ioc.beans.models.User;
import ru.yalymar.ioc.beans.storages.interfaces.Storage;

import java.util.ArrayList;
import java.util.List;

public class JdbcStorage implements Storage {

    List<User> list = new ArrayList<>();

    @Override
    public int add(User user) {
        return this.list.add(user) ? 1 : -1;
    }

    @Override
    public User get(User user) {
        User result = null;
        for (User u : this.list){
            if(u.equals(user)){
                result = u;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean update(User user, User newUser) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }
}
