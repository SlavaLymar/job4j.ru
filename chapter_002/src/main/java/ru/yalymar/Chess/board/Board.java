package ru.yalymar.Chess.board;

import ru.yalymar.Chess.cell.Cell;
import ru.yalymar.Chess.chessexceptions.FigureNotFoundException;
import ru.yalymar.Chess.chessexceptions.ImposibleMoveException;
import ru.yalymar.Chess.chessexceptions.OccupiedWayException;
import ru.yalymar.Chess.figure.Bishop;
import ru.yalymar.Chess.figure.Figure;

public class Board {

    private final static int LENGTH = 8;
    private final static int WIDTH = 8;
    private final static int NUMBEROFFIGURES = 32;
    private Figure[] figures = new Figure[NUMBEROFFIGURES];
    private Cell[][] cells = new Cell[LENGTH][WIDTH];

    public Board() {
        this.cells = getCells();
        this.figures = fillFigures();
    }

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

    public Figure[] fillFigures() {
        Figure[] result = new Figure[NUMBEROFFIGURES];
        for (int i = 0; i<NUMBEROFFIGURES; i++){
            result[0] = new Bishop(cells[7][2], true); // create my Bishop
        }
        return result;
    }

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

    public static int getLENGTH() {

        return LENGTH;
    }

    public static int getWIDTH() {

        return WIDTH;
    }

    public Figure[] getFigures() {
        return figures;
    }
}
