package ru.lymar.foodstore.food;

import java.time.LocalDate;

public class Bread extends Food{
    public Bread(String name, LocalDate expaireDate, LocalDate createDate, float price, String disscount) {
        super(name, expaireDate, createDate, price, disscount);
    }
}
