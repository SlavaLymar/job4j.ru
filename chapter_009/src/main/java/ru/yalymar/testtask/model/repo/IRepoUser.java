package ru.yalymar.testtask.model.repo;

import ru.yalymar.testtask.model.Address;
import ru.yalymar.testtask.model.User;
import java.util.List;

public interface IRepoUser {

    int add();

    List<User> findByAddress(Address address);

    List<User> findByRole(Address address);

    List<User> findByTypeOfMusic(Address address);

}
