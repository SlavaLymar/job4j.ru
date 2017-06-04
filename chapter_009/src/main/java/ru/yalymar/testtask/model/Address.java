package ru.yalymar.testtask.model;

/**
 * @author slavalymar
 * @version 1
 * @since 04.06.2017
 */
public class Address {

    private int id;
    private String address;

    public Address(String address) {
        this.address = address;
    }

    public Address(int id, String address) {
        this.id = id;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }
}
