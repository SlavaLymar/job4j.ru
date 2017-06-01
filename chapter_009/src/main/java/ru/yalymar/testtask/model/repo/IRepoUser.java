package ru.yalymar.testtask.model.repo;

import ru.yalymar.testtask.model.Address;
import ru.yalymar.testtask.model.Role;
import ru.yalymar.testtask.model.TypeOfMusic;
import ru.yalymar.testtask.model.User;
import java.util.List;

public interface IRepoUser {

    int add(User user);

    List<User> findByAddress(Address address);

    List<User> findByRole(Role role);

    List<User> findByTypeOfMusic(TypeOfMusic typeOfMusic);

}
