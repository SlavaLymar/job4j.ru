package ru.yalymar.orderbook.models;

import java.util.HashMap;
import java.util.TreeMap;

public class OrderBook {

    private String name;
    private HashMap<Integer, Order> orders;
    private TreeMap<Float, Order> sortedByPriceOrders;

    public OrderBook(String name) {
        this.name = name;
        this.orders = new HashMap<>();
        this.sortedByPriceOrders = new TreeMap<>();
    }

    public String getName() {
        return this.name;
    }

    public HashMap<Integer, Order> getOrders() {
        return this.orders;
    }

    public TreeMap<Float, Order> getSortedByPriceOrders() {
        return this.sortedByPriceOrders;
    }

    public boolean addOrder(Order order){
        boolean result = false;
        if(this.orders.put(order.getOrderID(), order) != null
            && this.sortedByPriceOrders.put(order.getPrice(), order) != null) {
            result = true;
        }
        return result;
    }

    public boolean removeOrder(int id){
        boolean result = false;
        if(this.sortedByPriceOrders.remove(this.returnFloatKey(id)) != null
                && this.orders.remove(id) != null) {
            result = true;
        }
        return result;
    }

    public Float returnFloatKey(int id) {
        return this.orders.get(id).getPrice();
    }




}
