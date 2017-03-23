package ru.yalymar.orderbook.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @since 23.03.2017
 * @version 1
 */
public class BookContainer {

    /**
     * list of books
     */
    private List<Book> list = new ArrayList<>();
    
    public List<Book> getList() {
        return this.list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

}
