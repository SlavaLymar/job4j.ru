package ru.lymar.lsp.food;

import java.time.LocalDate;

public class Tomato extends Vegetables {

    public Tomato(String name, LocalDate expiryDate,
                  LocalDate createDate, double price, String discountPercent) {
        super(name, expiryDate, createDate, price, discountPercent);
    }
}
