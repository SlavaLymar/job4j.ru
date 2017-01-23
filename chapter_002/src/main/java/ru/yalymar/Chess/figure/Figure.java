package ru.yalymar.Chess.figure;

import ru.yalymar.Chess.cell.Cell;
import ru.yalymar.Chess.chessexceptions.ImposibleMoveException;

/**
 * @author slavalymar
 * @since 23.01.2017
 * @version 1
 */
public abstract class Figure {

    private Cell position;
    private boolean flag; // true = my, false = enemy;
    protected int id;

    public Figure(Cell position, boolean flag) {
        this.position = position;
        this.flag = flag;
    }

    /**
     * @param dist
     * @return Cell[]
     * @throws ImposibleMoveException
     */
    public abstract Cell[] way(Cell dist) throws ImposibleMoveException;

    /**
     * @param dist
     * @return Figure
     * @throws ImposibleMoveException
     */
    public abstract Figure clone(Cell dist) throws ImposibleMoveException;

    /**
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * @return Cell
     */
    public Cell getPosition() {
        return position;
    }

    /**
     * @return boolean
     */
    public boolean isFlag() {
        return flag;
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
        if((!(obj instanceof Figure))){
            return false;
        }
        Figure figure = (Figure) obj;
        if(this.getId() == figure.getId() && this.getPosition().equals(figure.getPosition())){
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
        result = PRIME * result + getId();
        result = PRIME * result + getPosition().getX();
        result = PRIME * result + getPosition().getY();
        return result;
    }
}
