package ru.lymar.foodstore.controlQuality;

import ru.lymar.foodstore.food.Food;
import ru.lymar.foodstore.store.Move;
import ru.lymar.foodstore.store.WareHouse;
import java.util.Calendar;

public class ControlQuality {

    private Move move;
    private int days;
    private float freshnessPercent;

    public int getDays() {
        return days;
    }

    public void calculateDays(Food food){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(food.getExpairyDate());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(food.getCreateDate());
        long difference = calendar1.getTimeInMillis() - calendar2.getTimeInMillis();
        long result = difference/(24*60*60*1000);
        this.days =(int) result;
    }

    public int freshness(Food food){
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(food.getCreateDate());
        long difference = calendar1.getTimeInMillis() - calendar2.getTimeInMillis();
        long result = difference/(24*60*60*1000);
        System.out.println(result);
        return (int) result;
    }

    public void executeMove(Food food){
        this.calculateDays(food);
        this.freshnessPercent = this.freshness(food);
        if(this.freshnessPercent <= 25){
            this.move = new WareHouse();
            this.move.add(food);
        }
    }
}
