package ru.yalymar.Chess.figure;

import ru.yalymar.Chess.cell.Cell;
import ru.yalymar.Chess.chessexceptions.ImposibleMoveException;

public abstract class Figure {

    private Cell position;
    private boolean flag; // true = my, false = enemy;
    protected int id;

    public Figure(Cell position, boolean flag) {
        this.position = position;
        this.flag = flag;
    }

    public abstract Cell[] way(Cell dist) throws ImposibleMoveException;

    public abstract Figure clone(Cell dist);

    public int getId() {
        return id;
    }

    public Cell getPosition() {
        return position;
    }

    public boolean isFlag() {
        return flag;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if((obj instanceof Figure)){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + getId();
        return result;
    }
}
