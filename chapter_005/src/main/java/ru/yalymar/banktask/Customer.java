package ru.yalymar.banktask;

import java.util.Date;

/**
 * @author slavalymar
 * @since 27.03.2017
 * @version 1
 */
public class Customer {

    /**
     * customer`s id
     */
    private int id;

    /**
     * time of come in
     */
    private Date enter;

    /**
     * time of come out
     */
    private Date exit;

    public Customer(int id) {
        this.enter = new Date();
        this.id = id;
    }

    public Date getEnter() {
        return this.enter;
    }

    public Date getExit() {
        return this.exit;
    }

    public void setExit(Date exit) {
        this.exit = exit;
    }

    public int getId() {
        return this.id;
    }

    /** compare objects
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return id == customer.id;
    }

    /** calculate hashcode
     * @return int
     */
    @Override
    public int hashCode() {
        return id;
    }
}
