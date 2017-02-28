package ru.lymar.lsp.storedecorator;

import ru.lymar.lsp.food.Vegetables;

import java.util.List;

public interface RefrigeratorStore {

    /** add a food to list
     * @param food
     */
    boolean add(Vegetables food);

    /**
     * getter
     */
    List<Vegetables> getList();
}
