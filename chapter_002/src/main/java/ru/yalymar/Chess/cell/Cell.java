package ru.yalymar.chess.cell;

/**
 * @author slavalymar
 * @since 23.01.2017
 * @version 1
 */
public class Cell {

    private int x;
    private int y;
    private String color;

    public Cell(int y, int x, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**
     * @return String
     */
    public String getColor() {
        return color;
    }

    /**
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     * @param obj
     * @return boolean
     */
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

    /**
     * @return int
     */
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
