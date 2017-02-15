package ru.lymar.srp;

import ru.lymar.srp.calculate.Calculate;

public class Calculator {

    private Calculate calculate;

    public Calculator(Calculate calculate) {
        this.calculate = calculate;
    }

    public float action(float a, float b){
        return calculate.execute(a,b);
    }



}
