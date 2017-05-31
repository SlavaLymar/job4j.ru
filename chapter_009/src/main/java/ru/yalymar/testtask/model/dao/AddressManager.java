package ru.yalymar.testtask.model.dao;

import ru.yalymar.testtask.model.Address;
import java.util.List;

public class AddressManager extends Manager<Address> {

    @Override
    public int create(Address address) {
        return 0;
    }

    @Override
    public List<Address> getAll() {
        return null;
    }

    @Override
    public Address getById(int id) {
        return null;
    }

    @Override
    public int edit(int id, Address address) {

    }

    @Override
    public int remove(int id) {

    }
}
