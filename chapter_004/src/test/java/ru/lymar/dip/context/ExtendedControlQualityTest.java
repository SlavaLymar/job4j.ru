package ru.lymar.dip.context;

import org.junit.Test;
import ru.lymar.dip.food.*;
import java.time.LocalDate;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ExtendedControlQualityTest {

    @Test
    public void whenAddFoodToAllStoresThenResortFromWareHouseToTrash(){

        //warehouse
        Food apple = new Apple("Sezonnoe", LocalDate.now().plusMonths(12),
                LocalDate.now().minusMonths(2), 50.0, "50");

        //extended warehouse
        Food apple1 = new Apple("Sezonnoe12", LocalDate.now().plusMonths(12),
                LocalDate.now().minusMonths(2), 50.0, "50");

        //reproduct
        ReproductFood milk = new Milk("Milk", LocalDate.now().minusDays(2),
                LocalDate.now().minusMonths(2), 50.0, "50");

        //vegetables
        Vegetables tomato = new Tomato("Red Tomato", LocalDate.now().plusMonths(1),
                LocalDate.now().minusMonths(1), 50.0, "50");

        //Shop
        Food carrot = new Carrot("Dlinnaya", LocalDate.now().plusMonths(2),
                LocalDate.now().minusMonths(1), 50.0, "50");

        //trash
        Food bread = new Bread("Borodinskiy", LocalDate.now().minusDays(2),
                LocalDate.now().minusMonths(2), 50.0, "50");

        ExtendedControlQuality ecq = new ExtendedControlQuality(3);
        ecq.fillStore();

        // add food to stores
        ecq.selectStrategy(apple);
        ecq.selectStrategy(apple1);
        ecq.selectStrategy(milk);
        ecq.selectStrategy(tomato);
        ecq.selectStrategy(carrot);
        ecq.selectStrategy(bread);

        //set expiry date to become expired
        ecq.getWareHouse().getList().get(0).setExpiryDate(LocalDate.now().minusDays(2));
        ecq.resort();

        //set expiry date for expected foods
        apple.setExpiryDate(LocalDate.now().minusDays(2));

        //assert block
        assertThat(apple, is(ecq.getStore().get(2).getList().get(1)));
        assertThat(apple1, is(ecq.getExtendedWareHouse().getList().get(0)));
        assertThat(milk, is(ecq.getReproductStore().get(0).getList().get(0)));
        assertThat(tomato, is(ecq.getRefrigeratorStore().get(0).getList().get(0)));
        assertThat(carrot, is(ecq.getStore().get(0).getList().get(0)));
        assertThat(bread, is(ecq.getStore().get(2).getList().get(0)));
    }

    @Test
    public void whenAddFoodInAllStoreButSomeStoreIsNull(){
        //Shop
        Food carrot = new Carrot("Dlinnaya", LocalDate.now().plusMonths(2),
                LocalDate.now().minusMonths(1), 50.0, "50");

        ExtendedControlQuality ecq = new ExtendedControlQuality(3);
        ecq.fillStore();
        ecq.selectStrategy(carrot);
        ecq.resort();
        assertThat(ecq.getStore().get(0).getList().get(0), is(carrot));
    }

}