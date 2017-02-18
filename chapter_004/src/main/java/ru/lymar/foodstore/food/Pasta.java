package ru.lymar.foodstore.food;

import java.time.LocalDate;

public class Pasta extends Food{
    public Pasta(String name, LocalDate expairyDate, LocalDate createDate, float price, String discount) {
        super(name, expairyDate, createDate, price, discount);
    }
}
