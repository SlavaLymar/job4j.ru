package ru.yalymar.orderbook.models;

import java.util.TreeMap;

public class OrderBook {

    private String name;
    private TreeMap<Integer, Order> orders;


    public OrderBook(String name) {
        this.name = name;
        this.orders = new TreeMap<>();
    }

    public String getName() {
        return this.name;
    }

    public boolean addOrder(Order order){
        boolean result = false;
        if(this.orders.put(order.getOrderID(), order) != null) {
            result = true;
        }
        return result;
    }

    public boolean removeOrder(int id){
        boolean result = false;
        if(this.orders.remove(id) != null) {
            result = true;
        }
        return result;
    }
}
