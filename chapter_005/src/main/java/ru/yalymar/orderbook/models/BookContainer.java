package ru.yalymar.orderbook.models;

import java.util.ArrayList;
import java.util.List;


public class BookContainer {

    private List<OrderBook> list = new ArrayList<>();
    
    public List<OrderBook> getList() {
        return this.list;
    }

    public void setList(List<OrderBook> list) {
        this.list = list;
    }

}
