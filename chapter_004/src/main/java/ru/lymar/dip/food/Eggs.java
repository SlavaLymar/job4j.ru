package ru.lymar.dip.food;

import java.time.LocalDate;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public class Eggs extends Food {
    public Eggs(String name, LocalDate expiryDate, LocalDate createDate, double price, String discountPercent) {
        super(name, expiryDate, createDate, price, discountPercent);
    }
}
