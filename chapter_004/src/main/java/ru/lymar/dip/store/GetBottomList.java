package ru.lymar.dip.store;

import ru.lymar.dip.food.Food;
import java.util.List;

/**
 * @author slavalymar
 * @since 24.02.2017
 * @version 1
 */
public interface GetBottomList extends Store{

    List <Food> getBottomList();

    List<Food> combineLists();

}
