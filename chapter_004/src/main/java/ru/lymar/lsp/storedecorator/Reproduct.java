package ru.lymar.lsp.storedecorator;

import ru.lymar.lsp.food.ReproductFood;
import java.util.List;

/**
 * @author slavalymar
 * @since 19.02.2017
 * @version 1
 */
public interface Reproduct {

    /** add a food to list
     * @param food
     */
    boolean add(ReproductFood food);

    /**
     * getter
     */
    List<ReproductFood> getList();
}
