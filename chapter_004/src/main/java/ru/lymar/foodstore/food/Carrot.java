package ru.lymar.foodstore.food;

import java.time.LocalDate;

public class Carrot extends Food{
    public Carrot(String name, LocalDate expaireDate, LocalDate createDate, float price, String disscount) {
        super(name, expaireDate, createDate, price, disscount);
    }
}
