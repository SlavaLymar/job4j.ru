package ru.yalymar.ioc.storages.storages;

import ru.yalymar.ioc.storages.models.User;
import ru.yalymar.ioc.storages.storages.interfaces.Storage;
import java.util.ArrayList;
import java.util.List;

public class MemoryStorage implements Storage {

    private List<User> list = new ArrayList<>();

    @Override
    public int add(User user) {
        return this.list.add(user) ? 1 : -1;
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
    public boolean update(User user, User newUser) {
        boolean result = false;
        for(User u: this.list){
            if(u.getId() == user.getId()){
                u = newUser;
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        return this.list.remove(user);
    }


}
