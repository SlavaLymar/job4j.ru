package ru.lymar.foodstore.food;

import java.time.LocalDate;

public class Pasta extends Food{

    public Pasta(String name, LocalDate expiryDate, LocalDate createDate, double price, String discountPercent) {
        super(name, expiryDate, createDate, price, discountPercent);
    }
}
