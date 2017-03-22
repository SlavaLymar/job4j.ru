package ru.yalymar.orderbook.models;

public class Order {

    private OrderBook orderBook;
    private int orderID;
    private String operation;
    private short volume;
    private float price;

    public Order(OrderBook orderBook, int orderID,
                 String operation, short volume, float price) {

        this.orderBook = orderBook;
        this.orderID = orderID;
        this.operation = operation;
        this.volume = volume;
        this.price = price;
    }

    public String operation() {
        return operation;
    }

    public float getPrice() {
        return price;
    }

    public short getVolume() {
        return volume;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderBook(OrderBook orderBook) {
        this.orderBook = orderBook;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setVolume(short volume) {
        this.volume = volume;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
