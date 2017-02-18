package ru.lymar.foodstore;

import ru.lymar.foodstore.controlQuality.ControlQuality;
import ru.lymar.foodstore.food.Apple;
import ru.lymar.foodstore.food.Food;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");

    public void init(){
        Food apple = new Apple("Red Apple",
                                new Date(2017, 06, 17),
                                new Date(2017, 01, 17),
                                100, "50%");
        ControlQuality cq = new ControlQuality();

    }

    public static void main(String[] args) {
        new App().init();
    }
}
