package ru.yalymar.testtask.model.dao;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.testtask.model.Address;
import ru.yalymar.testtask.model.Role;
import ru.yalymar.testtask.model.User;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class RoleManagerTest {

    private DAOFabric daoFabric;
    private RoleManager manager;

    @Before
    public void init(){
        this.daoFabric = new DAOFabric();
        this.manager = this.daoFabric.getRoleManager();
    }

    @Test
    public void whenGetRoleByIdShouldGetIt(){
        Role role = this.manager.getById(1);
        assertNotNull(role);
    }

    @Test
    public void whenAddRoleShouldGetId(){
        Role role = new Role("banan");
        int id = this.manager.create(role);
        assertTrue(id > 0);

        this.manager.remove(id);
    }

    @Test
    public void whenRemoveRoleShouldGetInt(){
        Role role = new Role("banan");
        int id = this.manager.create(role);
        int i = this.manager.remove(id);
        assertTrue(i > 0);
    }

    @Test
    public void whenGetAllAddressesShouldGetIt(){
        List<Role> roles = this.manager.getAll();
        assertTrue(roles.size() > 0);
    }

    @Test
    public void whenEditAddressShouldGetNewOne(){
        Role role = new Role("banan");
        Role newRole = new Role("orange");
        int id = this.manager.create(role);

        int i = this.manager.edit(id, newRole);
        assertTrue(i > 0);

        this.manager.remove(id);
    }

    @Test
    public void whenGetAddressesByRoleShouldGetOnes(){
        List<Address> addresses = this.manager.getAddresses(new Role("user"));
        assertNotNull(addresses);
    }

    @Test
    public void whenGetUsersByRoleShouldGetOnes(){
        List<User> users = this.manager.getUsers(new Role("user"));
        assertNotNull(users);
    }
}