package ru.yalymar.Chess.figure;

import ru.yalymar.Chess.cell.Cell;
import ru.yalymar.Chess.chessexceptions.ImposibleMoveException;

import static java.lang.Math.abs;

public class Bishop extends Figure {

    public Bishop(Cell position, boolean flag) {
        super(position, flag);
        id = 1;
    }

    @Override
    public Cell[] way(Cell dist) throws ImposibleMoveException {

        Cell startCell = this.getPosition();
        if(!startCell.getColor().equals(dist.getColor())) throw new ImposibleMoveException("Imposible move!");

        int distanceX = dist.getX()-startCell.getX();
        int distanceY = dist.getY()-startCell.getY();
        Cell[] result = new Cell[abs(distanceX)];

        if(abs(distanceX) == abs(distanceY)
                && startCell.getX()>=0 && startCell.getX()<=7 && startCell.getY()>=0 && startCell.getY()<=7
                && dist.getX()>=0 && dist.getX()<=7 && dist.getY()>=0 && dist.getY()<=7) {

            for (int i = 0; i < abs(distanceY); i++) {
                result[i] = new Cell(startCell.getY() +(i + 1) * distanceY / abs(distanceY),
                        startCell.getX() + (i + 1) * distanceX / abs(distanceX), startCell.getColor());
                }
        }
        else throw new ImposibleMoveException("Imposible move!");

        return result;
    }

    @Override
    public Bishop clone(Cell dist) throws ImposibleMoveException{
        if(dist.getY() <= 7 && dist.getY()>=0 && dist.getX() <= 7 && dist.getX()>=0){
            return new Bishop(new Cell(dist.getY(), dist.getX(), dist.getColor()), isFlag());
        }
        else throw new ImposibleMoveException("Imposible move!");
    }
}
