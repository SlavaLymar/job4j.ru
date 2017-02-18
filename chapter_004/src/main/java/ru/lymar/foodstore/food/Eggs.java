package ru.lymar.foodstore.food;

import java.time.LocalDate;

public class Eggs extends Food{
    public Eggs(String name, LocalDate expaireDate, LocalDate createDate, float price, String disscount) {
        super(name, expaireDate, createDate, price, disscount);
    }
}
