package ru.yalymar.testtask.model.repo;

import ru.yalymar.testtask.model.Address;
import ru.yalymar.testtask.model.User;
import java.util.List;

public interface IRepoRole {

    List<Address> getAddresses();

    List<User> getUsers();
}
