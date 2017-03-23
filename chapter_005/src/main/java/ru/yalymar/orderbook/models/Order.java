package ru.yalymar.orderbook.models;

public class Order {

    private final int id;

    private final String operation;

    private final int volume;

    private final double price;

    public Order(int id, String operation, int volume, double price) {
        this.id = id;
        this.operation = operation;
        this.volume = volume;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getOperation() {
        return operation;
    }

    public int getVolume() {
        return volume;
    }

    public double getPrice() {
        return price;
    }
}
