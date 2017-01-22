package ru.yalymar.Chess.board;


import ru.yalymar.Chess.cell.Cell;
import ru.yalymar.Chess.figure.Bishop;
import ru.yalymar.Chess.figure.Figure;

public class Board {
    private static Figure[] figures;
    private static Cell[][] cells;
    private static Board ourInstance = new Board(getCells(), getFigures());
    private final static int LENGTH = 8;
    private final static int WIDTH = 8;
    private final static int NUMBEROFFIGURES = 32;

    public static Board getInstance() {
        return ourInstance;
    }

    private Board(Cell[][] cells, Figure[] figures) {
        this.cells = cells;
        this.figures = figures;
    }

    private static final Cell[][] getCells(){
        Cell[][] result = new Cell[LENGTH][WIDTH];
        int color = 0;
        for(int i = 0; i<LENGTH; i++){
            for(int j = 0; j<WIDTH; j++){
                result[i][j] = new Cell(j, i, (color++%2 == 0? "Black": "White"));
            }
        }
        return result;
    }

    private static Figure[] getFigures() {
        Figure[] result = new Figure[NUMBEROFFIGURES];
        for (int i = 0; i<NUMBEROFFIGURES; i++){
            result[0] = new Bishop(cells[0][2], true); // create my Bishop
        }
        return result;
    }

    /** return picked cell
     * @return Cell
     */
    public Cell getPositionOfFigure(){
        return cells[0][2];
    }

    public static int getLENGTH() {
        return LENGTH;
    }

    public static int getWIDTH() {
        return WIDTH;
    }
}
