package ru.yalymar.Chess.figure;


import ru.yalymar.Chess.board.Board;
import ru.yalymar.Chess.cell.Cell;
import ru.yalymar.Chess.chessexceptions.ImposibleMoveException;

import static java.lang.Math.abs;

public class Bishop extends Figure {

    public Bishop(Cell position, boolean flag) {
        super(position, flag);
    }

    @Override
    public Cell[] way(Cell dist) throws ImposibleMoveException {

        Cell startCell = Board.getInstance().getPositionOfFigure();
        if(!startCell.getColor().equals(dist.getColor())) throw new ImposibleMoveException("Imposible move!");

        Cell[] result;
        int distanceX = startCell.getX()-dist.getX();
        int distanceY = startCell.getY()-dist.getY();

        if(abs(distanceX) == abs(distanceY)
                && startCell.getX()>=0 && startCell.getX()<=7 && startCell.getY()>=0 && startCell.getY()<=7
                && dist.getX()>=0 && dist.getX()<=7 && dist.getY()>=0 && dist.getY()<=7) {

            result = new Cell[abs(distanceX)];

            for (int i = 0; i < abs(distanceY); i++) {
                result[i] = new Cell(startCell.getX() + (i + 1) * distanceX / abs(distanceX),
                    startCell.getY() + (i + 1) * distanceY / abs(distanceY), startCell.getColor());
                }
        }
        else throw new ImposibleMoveException("Imposible move!");
        
        return result;
    }

    private boolean move(Cell source, Cell dist){

        return false;
    }
}
