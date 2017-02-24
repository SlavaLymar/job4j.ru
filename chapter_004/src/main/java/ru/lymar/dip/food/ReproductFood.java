package ru.lymar.dip.food;

import java.time.LocalDate;

public abstract class ReproductFood extends Food {

    private boolean canReproduct = true;

    public ReproductFood(String name, LocalDate expiryDate,
                         LocalDate createDate, double price, String discountPercent) {
        super(name, expiryDate, createDate, price, discountPercent);
    }

    public boolean isCanReproduct() {
        return this.canReproduct;
    }
}
