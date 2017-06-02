package ru.yalymar.testtask.model.dao;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.testtask.model.Address;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AddressManagerTest {

    private DAOFabric daoFabric;
    private AddressManager manager;

    @Before
    public void init(){
        this.daoFabric = new DAOFabric();
        this.manager = this.daoFabric.getAddressManager();
    }

    @Test
    public void whenAddAddressShouldGetIt(){
        Address address = new Address("Bolshaya Gruzinskaya 1");
        int id = this.manager.create(address);
        assertTrue(id > 0);

        this.manager.remove(id);
    }

    @Test
    public void whenRemoveAddressShouldGetInt(){
        Address address = new Address("Bolshaya Gruzinskaya 1");
        int id = this.manager.create(address);
        int i = this.manager.remove(id);
        assertTrue(i > 0);
    }

    @Test
    public void whenGetAddressByIdShouldGetIt(){
        Address address = this.manager.getById(1);
        assertNotNull(address);
    }

    @Test
    public void whenGetAllAddressesShouldGetIt(){
        List<Address> addresses = this.manager.getAll();
        assertTrue(addresses.size() > 0);
    }

    @Test
    public void whenEditAddressShouldGetNewOne(){
        Address address = new Address("Bolshaya Gruzinskaya 1");
        Address newAddress = new Address("Krasnaya Presnya 12");
        int id = this.manager.create(address);

        int i = this.manager.edit(id, newAddress);
        assertTrue(i > 0);

        this.manager.remove(id);
    }
}