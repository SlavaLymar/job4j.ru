package ru.lymar.lsp.food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public abstract class Food {

    /**
     * name of food
     */
    private String name;

    /**
     * date of expiry
     */
    private LocalDate expiryDate;

    /**
     * date of create
     */
    private LocalDate createDate;

    /**
     * cost of a food
     */
    private double price;

    /**
     * percent to decrease price
     */
    private String discountPercent;

    /**
     * percent of corruption
     */
    private double corruptionPercent;

    public Food(String name, LocalDate expiryDate, LocalDate createDate,
                double price, String discountPercent) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discountPercent = discountPercent;
        this.corruption();
    }

    /**
     * calculate percent of corruption
     */
    public void corruption(){
        LocalDate currentDate = LocalDate.now();
        long timeFromCreateToCurrentTime = ChronoUnit.DAYS.between(this.getCreateDate(), currentDate);
        long timeFromCreateToExpireTime = ChronoUnit.DAYS.between(this.getCreateDate(), this.getExpiryDate());
        this.corruptionPercent =  1.0 * timeFromCreateToCurrentTime / timeFromCreateToExpireTime * 100;
    }

    /**
     * getters
     */
    public String getName() {
        return name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public double getPrice() {
        return price;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public double getCorruptionPercent() {
        return corruptionPercent;
    }

    /**
     * setter
     */
    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public void setDiscountPercent(String discountPercent) {
        this.discountPercent = discountPercent;
    }
}
