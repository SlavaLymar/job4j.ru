package ru.lymar.foodstore.context;

import org.junit.Test;
import ru.lymar.foodstore.food.*;
import ru.lymar.foodstore.store.WareHouse;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ControlQualityTest {

    @Test
    public void addFoodInShopWhenFoodIsFresh(){
        Food carrot = new Carrot("Dlinnaya", LocalDate.now().plusMonths(2),
                LocalDate.now().minusMonths(1), 50.0, "50");
        ControlQuality cq = new ControlQuality(carrot);
        cq.selectStrategy();
        assertThat(cq.getStore()[0].getList().get(0), is(carrot));
    }

    @Test
    public void addFoodInShopWhenFoodIsNotFresh(){
        Food eggs = new Eggs("Derevenskie", LocalDate.now().plusWeeks(1),
                LocalDate.now().minusMonths(1), 50.0, "50");
        ControlQuality cq = new ControlQuality(eggs);
        cq.selectStrategy();
        System.out.println(eggs.getCorruptionPercent());
        assertThat(cq.getStore()[0].getList().get(0), is(eggs));
    }

    @Test
    public void addFoodInWareHouse(){
        Food apple = new Apple("Sezonnoe", LocalDate.now().plusMonths(12),
                LocalDate.now().minusMonths(2), 50.0, "50");
        ControlQuality cq = new ControlQuality(apple);
        cq.selectStrategy();
        assertThat(cq.getStore()[1].getList().get(0), is(apple));
    }

    @Test
    public void addFoodInTrash(){
        Food bread = new Bread("Borodinskiy", LocalDate.now().minusDays(2),
                LocalDate.now().minusMonths(2), 50.0, "50");
        ControlQuality cq = new ControlQuality(bread);
        cq.selectStrategy();
        assertThat(cq.getStore()[2].getList().get(0), is(bread));
    }
}