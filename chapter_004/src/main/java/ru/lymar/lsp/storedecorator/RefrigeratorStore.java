package ru.lymar.lsp.storedecorator;

import ru.lymar.lsp.food.Vegetables;
import java.util.List;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
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
