package ru.lymar.dip.context;

import org.junit.Test;
import ru.lymar.dip.food.Apple;
import ru.lymar.dip.food.Bread;
import ru.lymar.dip.food.Carrot;
import ru.lymar.dip.food.Food;
import java.time.LocalDate;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ControlQualityTest {

    @Test
    public void whenAddFoods(){

        //warehouse
        Food apple = new Apple("Sezonnoe", LocalDate.now().plusMonths(12),
                LocalDate.now().minusMonths(2), 50.0, "50");

        //Shop
        Food carrot = new Carrot("Dlinnaya", LocalDate.now().plusMonths(2),
                LocalDate.now().minusMonths(1), 50.0, "50");

        //trash
        Food bread = new Bread("Borodinskiy", LocalDate.now().minusDays(2),
                LocalDate.now().minusMonths(2), 50.0, "50");

        ControlQuality cq = new ControlQuality(3);
        cq.fillStore();
        cq.selectStrategy(apple);
        cq.selectStrategy(carrot);
        cq.selectStrategy(bread);
        assertThat(cq.getStore().get(1).getList().get(0), is(apple));
        assertThat(cq.getStore().get(0).getList().get(0), is(carrot));
        assertThat(cq.getStore().get(2).getList().get(0), is(bread));
    }

    @Test
    public void whenAddFoodsAndResortToTrash(){

        //warehouse
        Food apple = new Apple("Sezonnoe", LocalDate.now().plusMonths(12),
                LocalDate.now().minusMonths(2), 50.0, "50");

        //Shop
        Food carrot = new Carrot("Dlinnaya", LocalDate.now().plusMonths(2),
                LocalDate.now().minusMonths(1), 50.0, "50");

        //trash
        Food bread = new Bread("Borodinskiy", LocalDate.now().minusDays(2),
                LocalDate.now().minusMonths(2), 50.0, "50");

        //init
        ControlQuality cq = new ControlQuality(3);
        cq.fillStore();

        // add food to stores
        cq.selectStrategy(apple);
        cq.selectStrategy(carrot);
        cq.selectStrategy(bread);

        //set expiry date to become expired
        cq.getStore().get(1).getList().get(0).setExpiryDate(LocalDate.now().minusDays(2));
        cq.getStore().get(0).getList().get(0).setExpiryDate(LocalDate.now().minusDays(2));
        cq.getStore().get(2).getList().get(0).setExpiryDate(LocalDate.now().minusDays(2));
        cq.resort();

        //set expiry date for expected foods
        apple.setExpiryDate(LocalDate.now().minusDays(2));
        carrot.setExpiryDate(LocalDate.now().minusDays(2));
        bread.setExpiryDate(LocalDate.now().minusDays(2));

        //assert block
        assertThat(cq.getStore().get(2).getList().get(0), is(bread));
        assertThat(cq.getStore().get(2).getList().get(1), is(carrot));
        assertThat(cq.getStore().get(2).getList().get(2), is(apple));

    }
}