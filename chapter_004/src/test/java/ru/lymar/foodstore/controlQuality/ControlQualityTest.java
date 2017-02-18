package ru.lymar.foodstore.controlQuality;

import org.junit.Test;
import ru.lymar.foodstore.food.Apple;
import ru.lymar.foodstore.food.Food;
import java.util.Date;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ControlQualityTest {

    @Test
    public void GetDaysTest() {
        ControlQuality cq = new ControlQuality();
        Food apple = new Apple("Red Apple",
                new Date(2017, 06, 30),
                new Date(2017, 01, 01),
                100, "50%");
        cq.calculateDays(apple);
        assertThat(cq.getDays(), is(179));
    }

    @Test
    public void freshnessTest() {
        ControlQuality cq = new ControlQuality();
        Food apple = new Apple("Red Apple",
                new Date(2017, 06, 30),
                new Date(2017, 01, 01),
                100, "50%");
        cq.executeMove(apple);
        assertThat(cq.getDays(), is(29));
    }
}