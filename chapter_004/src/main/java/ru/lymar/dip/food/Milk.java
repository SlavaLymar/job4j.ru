package ru.lymar.dip.food;

import java.time.LocalDate;

public class Milk extends ReproductFood {

    public Milk(String name, LocalDate expiryDate,
                LocalDate createDate, double price, String discountPercent) {
        super(name, expiryDate, createDate, price, discountPercent);
    }
}
