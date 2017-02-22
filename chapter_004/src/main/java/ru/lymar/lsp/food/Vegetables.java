package ru.lymar.lsp.food;

import java.time.LocalDate;

public abstract class Vegetables extends Food{

    private boolean isVegetables = true;

    public Vegetables(String name, LocalDate expiryDate,
                      LocalDate createDate, double price, String discountPercent) {
        super(name, expiryDate, createDate, price, discountPercent);
    }

    public boolean isVegetables() {
        return isVegetables;
    }
}
