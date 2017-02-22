package ru.lymar.lsp.store;

import ru.lymar.lsp.food.Food;
import java.util.List;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public interface Store {

    /** add a food to list
     * @param food
     */
    boolean add(Food food);

    /**
     * getter
     */
    List <Food> getList();

}
