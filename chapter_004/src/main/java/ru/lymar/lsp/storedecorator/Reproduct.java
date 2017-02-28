package ru.lymar.lsp.storedecorator;

import ru.lymar.lsp.food.ReproductFood;
import java.util.List;

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
