package ru.lymar.foodstore.food;


import java.time.LocalDate;

public class Milk extends Food{
    public Milk(String name, LocalDate expaireDate, LocalDate createDate, float price, String disscount) {
        super(name, expaireDate, createDate, price, disscount);
    }
}
