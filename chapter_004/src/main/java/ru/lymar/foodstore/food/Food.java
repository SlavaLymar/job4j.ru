package ru.lymar.foodstore.food;

import java.util.Date;

public abstract class Food {

    private String name;
    private Date expairyDate;
    private Date createDate;
    private float price;
    private String discount;

    public Food(String name, Date expairyDate, Date createDate, float price, String discount) {
        this.name = name;
        this.expairyDate = expairyDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public Date getExpairyDate() {
        return expairyDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public float getPrice() {
        return price;
    }

    public String getDiscount() {
        return discount;
    }
}
