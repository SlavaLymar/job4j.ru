package ru.lymar.testtask.field;

import org.junit.Test;

public class FieldTest {

    @Test
    public void whenPrintField(){
        GameField gm = new GameField(4);
        gm.fillFields();
        gm.print();
    }
}