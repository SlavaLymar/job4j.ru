package ru.yalymar.chess.board;

import org.junit.Test;
import ru.yalymar.chess.cell.Cell;
import ru.yalymar.chess.chessexceptions.FigureNotFoundException;
import ru.yalymar.chess.chessexceptions.ImposibleMoveException;
import ru.yalymar.chess.chessexceptions.OccupiedWayException;
import ru.yalymar.chess.figure.Bishop;
import ru.yalymar.chess.figure.Figure;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;

/**
 * @author slavalymar
 * @since 23.01.2017
 * @version 1
 */
public class BoardTest {

    Board board = Board.getBoard();

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
    public void fillFiguresTest() {
        Figure[] result = board.getFigures();
        Figure[] expected = new Figure[32];
        expected[0] = new Bishop(new Cell(7,2, "Black"),true);
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
    public void occupedWayTest()  {
        Cell dist = new Cell(3,6,"Black");
        Cell[] path = null;
        try {
            path = board.getFigures()[0].way(dist);
        }
        catch (ImposibleMoveException e){
            e.printStackTrace();
        }
        boolean result = false;
        try {
            result = board.occupedWay(path);
        } catch (OccupiedWayException e) {
            e.printStackTrace();
        }

        assertThat(result, is(true));
    }

    @Test
    public void getFigureTest() {
        Figure figure = board.getFigure(board.getCells()[7][2]);
        Figure expected = new Bishop(new Cell(7,2,"Black"), true);
        assertThat(figure, is(expected));
    }

    @Test
    public void figureFinderTest() {
        Cell source = new Cell(7,2,"Black");
        boolean result = false;
        try {
            result = board.figureFinder(source);
        } catch (FigureNotFoundException e) {
            e.printStackTrace();
        }
        assertThat(result, is(true));
    }

}