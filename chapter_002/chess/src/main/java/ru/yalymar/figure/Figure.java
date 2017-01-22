package ru.yalymar.figure;

import ru.yalymar.cell.Cell;
import ru.yalymar.chessexceptions.ImposibleMoveException;

public abstract class Figure {

    private Cell position;
    private boolean flag; // true = my, false = enemy;

    public Figure(Cell position, boolean flag) {
        this.position = position;
        this.flag = flag;
    }

    public abstract Cell[] way(Cell dist) throws ImposibleMoveException;
}
