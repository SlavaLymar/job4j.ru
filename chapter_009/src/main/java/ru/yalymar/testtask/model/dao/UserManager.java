package ru.yalymar.testtask.model.dao;

import ru.yalymar.testtask.model.Address;
import ru.yalymar.testtask.model.User;
import ru.yalymar.testtask.model.repo.IRepoUser;
import java.util.List;

public class UserManager extends Manager<User> implements IRepoUser{

    @Override
    public int create(User user) {
        return 0;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public int edit(int id, User user) {

    }

    @Override
    public int remove(int id) {

    }

    @Override
    public int add() {

    }

    @Override
    public List<User> findByAddress(Address address) {
        return null;
    }

    @Override
    public List<User> findByRole(Address address) {
        return null;
    }

    @Override
    public List<User> findByTypeOfMusic(Address address) {
        return null;
    }
}
