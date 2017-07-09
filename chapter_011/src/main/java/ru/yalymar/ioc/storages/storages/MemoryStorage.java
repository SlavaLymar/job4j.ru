package ru.yalymar.ioc.storages.storages;

import org.springframework.stereotype.Component;
import ru.yalymar.ioc.storages.models.User;
import ru.yalymar.ioc.storages.storages.interfaces.Storage;
import java.util.ArrayList;
import java.util.List;

@Component
public class MemoryStorage implements Storage {

    private List<User> list = new ArrayList<>();

    @Override
    public int add(User user) {
        return this.list.add(user) ? 1 : -1;
    }

    @Override
    public User get(User user) {
        User result = null;
        for (User u: this.list) {
            if(u.getId().equals(user.getId())){
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
            if(u.getId().equals(user.getId())){
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
