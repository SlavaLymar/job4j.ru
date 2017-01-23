package ru.yalymar.Chess.board;

import org.junit.Test;
import ru.yalymar.Chess.cell.Cell;
import ru.yalymar.Chess.chessexceptions.FigureNotFoundException;
import ru.yalymar.Chess.chessexceptions.ImposibleMoveException;
import ru.yalymar.Chess.chessexceptions.OccupiedWayException;
import ru.yalymar.Chess.figure.Bishop;
import ru.yalymar.Chess.figure.Figure;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;

public class BoardTest {


    private Board board = new Board();

    @Test
    public void boardTest(){
        Cell result = board.getCells()[0][2];
        Cell expected = new Cell(0,2,"White");
        assertThat(result, is(expected));
    }

    @Test
    public void getCellsTest() {
        Cell result = board.getCells()[0][7];
        Cell expected = new Cell(0,7,"Black");
        assertThat(result, is(expected));
    }

    @Test
    public void getFiguresTest() {
        Figure[] result = board.getFigures();
        Figure[] expected = new Figure[32];
        expected[0] = new Bishop(new Cell(0,2, "white"),true);
        assertArrayEquals(result, expected);
    }

    @Test
    public void moveTest() throws ImposibleMoveException, FigureNotFoundException, OccupiedWayException {
        Cell source = new Cell(7,2,"Black");
        Cell dist = new Cell(3,5,"Black");
        boolean result = board.move(source, dist);
        assertThat(result, is(false));

    }

    @Test
    public void deleteFigureTest() {

    }

    @Test
    public void occupedWayTest() {

    }

    @Test
    public void getFigureTest() {

    }

    @Test
    public void figureFinderTest() {

    }

}