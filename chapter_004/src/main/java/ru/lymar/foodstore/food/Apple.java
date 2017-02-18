package ru.lymar.foodstore.food;

import java.time.LocalDate;

public class Apple extends Food{

    public Apple(String name, LocalDate expaireDate, LocalDate createDate, float price, String disscount) {
        super(name, expaireDate, createDate, price, disscount);
    }
}
