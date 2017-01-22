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

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(!(obj instanceof Cell)){
            return false;
        }

        Cell cell = (Cell) obj;
        if(this.getX() == cell.getX() && this.getY() == cell.getY()){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + getX();
        result = PRIME * result + getY();
        return result;
    }

}
