package ru.yalymar.Chess.board;

import ru.yalymar.Chess.cell.Cell;
import ru.yalymar.Chess.chessexceptions.FigureNotFoundException;
import ru.yalymar.Chess.chessexceptions.ImposibleMoveException;
import ru.yalymar.Chess.chessexceptions.OccupiedWayException;
import ru.yalymar.Chess.figure.Bishop;
import ru.yalymar.Chess.figure.Figure;

/**
 * @author slavalymar
 * @since 23.01.2017
 * @version 1
 */
public class Board {

    private final static int LENGTH = 8;
    private final static int WIDTH = 8;
    private final static int NUMBEROFFIGURES = 32;
    private Figure[] figures = new Figure[NUMBEROFFIGURES];
    private Cell[][] cells = new Cell[LENGTH][WIDTH];
    private static Board board = new Board();

    private Board() {
        this.cells = getCells();
        this.figures = fillFigures();
    }

    public static Board getBoard() {
        return board;
    }

    /**
     * @return Cell[][]
     */
    public Cell[][] getCells(){
        Cell[][] result = new Cell[LENGTH][WIDTH];
        int color = 0;
        for(int i = 0; i<LENGTH; i++){
            for(int j = 0; j<WIDTH; j++){
                result[i][j] = new Cell(i, j, (color++%2 == 0? "Black": "White"));
            }
        }
        return result;
    }

    /**
     * @return Figure[]
     */
    public Figure[] fillFigures() {
        Figure[] result = new Figure[NUMBEROFFIGURES];
        for (int i = 0; i<NUMBEROFFIGURES; i++){
            result[0] = new Bishop(cells[7][2], true); // create my Bishop
        }
        return result;
    }

    /**
     * @param source
     * @param dist
     * @return boolean
     * @throws FigureNotFoundException
     * @throws ImposibleMoveException
     * @throws OccupiedWayException
     */
    public boolean move(Cell source, Cell dist) throws FigureNotFoundException,
            ImposibleMoveException, OccupiedWayException{
        boolean result = false;
        if(figureFinder(source)){
            Figure figure = getFigure(source);
            try {
                Cell[] wayOfFigure = figure.way(dist);
                if (occupedWay(wayOfFigure)) {
                    figure.clone(dist);
                    result = true;
                }
            }
            catch (ImposibleMoveException e){
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * @param cells
     * @return boolean
     * @throws OccupiedWayException
     */
    public boolean occupedWay(Cell[] cells) throws OccupiedWayException{
        boolean result = true;
        for (Cell cell: cells){
            for(Figure figure: figures){
                if(figure != null){
                    if(cell.equals(figure.getPosition())) {
                    result = false;
                    throw new OccupiedWayException("Occupied way!");
                    }
                }
            }
        }
        return result;
    }

    /**
     * @param cell
     * @return Figure
     */
    public Figure getFigure(Cell cell){
        Figure result = null;
        for(Figure figure: this.figures){
            if(figure.getPosition().equals(cell)){
                result = figure;
                break;
            }
        }
        return result;
    }

    /**
     * @param source
     * @return boolean
     * @throws FigureNotFoundException
     */
    public boolean figureFinder(Cell source) throws FigureNotFoundException{
        boolean result = false;
        for(Figure figure: figures) {
            if(figure != null){
                if (figure.getPosition().equals(source)) {
                result = true;
                break;
                }
            }
        }
        if(!result) throw new FigureNotFoundException("Figure wasn`t found!");
        return result;
    }

    /**
     * @return int
     */
    public static int getLENGTH() {

        return LENGTH;
    }

    /**
     * @return int
     */
    public static int getWIDTH() {

        return WIDTH;
    }

    /**
     * @return Figure[]
     */
    public Figure[] getFigures() {
        return figures;
    }
}
