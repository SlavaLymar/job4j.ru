package ru.lymar.testtask.field;

public class Cell {

    private int x;
    private int y;
    private String value;

    public Cell(int x, int y, String value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
