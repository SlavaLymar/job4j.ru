package ru.yalymar.Chess.cell;

public class Cell {

    private int x;
    private int y;
    private String color;

    public Cell(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
