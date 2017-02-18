package ru.lymar.foodstore.food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Food {

    private String name;
    private LocalDate expairyDate;
    private LocalDate createDate;
    private float price;
    private String discount;
    private double freshnessPercent;

    public Food(String name, LocalDate expairyDate, LocalDate createDate,
                float price, String discount) {
        this.name = name;
        this.expairyDate = expairyDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public void freshness(){
        LocalDate currentDate = LocalDate.now();
        long timeFromCreateToCurrentTime = ChronoUnit.DAYS.between(this.getCreateDate(), currentDate);
        long timeFromCreateToExpireTime = ChronoUnit.DAYS.between(this.getCreateDate(), this.getExpairyDate());
        this.freshnessPercent =  1.0 * timeFromCreateToCurrentTime / timeFromCreateToExpireTime * 100;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpairyDate() {
        return expairyDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public float getPrice() {
        return price;
    }

    public String getDiscount() {
        return discount;
    }

    public double getFreshnessPercent() {
        return freshnessPercent;
    }
}
