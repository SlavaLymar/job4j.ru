package ru.yalymar.orderbook.models;

import java.util.HashMap;
import java.util.Map;

/**
 * @author slavalymar
 * @since 23.03.2017
 * @version 1
 */
public class Book {

    /**
     * name of book
     */
    private final String name;

    /**
     * sell orders
     */
    private Map<Integer, Order> sellOrders;

    /**
     * buy orders
     */
    private Map<Integer, Order> buyOrders;

    public String getName() {
        return this.name;
    }

    public Book(String name) {
        this.name = name;
        this.sellOrders = new HashMap<>();
        this.buyOrders = new HashMap<>();
    }

    /** add order
     * @param order
     */
    public void addOrder(Order order){
        if("SELL".equals(order.getOperation())){
            this.sellOrders.put(order.getId(), order);
        }
        if("BUY".equals(order.getOperation())){
            this.buyOrders.put(order.getId(), order);
        }
    }

    /** remove order
     * @param id
     */
    public void removeOrder(int id){
        this.sellOrders.remove(id);
        this.buyOrders.remove(id);
    }

    /**
     * print book
     */
    public void printBook(){
        String str1 = String.format( "%s%s%s%s%s%s%s%s", "Order book: ", this.name, System.getProperty("line.separator"),
                "BID ", "ASK", System.getProperty("line.separator"),
                "Volume@Price - Volume@Price", System.getProperty("line.separator"));
        System.out.println(str1);

        MyTreeMap<Double, Integer> bids = new MyTreeMap<>();
        MyTreeMap<Double, Integer> asks = new MyTreeMap<>();

        for(Integer i : this.buyOrders.keySet()){
            bids.put(this.buyOrders.get(i).getPrice(), this.buyOrders.get(i).getVolume());
        }

        for(Integer i : this.sellOrders.keySet()){
            asks.put(this.sellOrders.get(i).getPrice(), this.sellOrders.get(i).getVolume());
        }

        while(bids.size() > 0 && asks.size() > 0){
            Map.Entry<Double, Integer> oHigh = bids.getHigh();
            String bid = String.format("%s%s%.2f", oHigh.getValue(), "@", oHigh.getKey());
            bids.remove(oHigh.getKey());

            Map.Entry<Double, Integer> oLow = asks.getLow();
            String ask = "------";
            if(oLow.getValue() != null && oLow.getKey() != null) {
                ask = String.format("%s%s%.2f", oLow.getValue(), "@", oLow.getKey());
                asks.remove(oLow.getKey());
            }

            String str = String.format( "%s%s%s%s", bid, " ", ask, System.getProperty("line.separator"));
            System.out.println(str);
        }
    }
}
