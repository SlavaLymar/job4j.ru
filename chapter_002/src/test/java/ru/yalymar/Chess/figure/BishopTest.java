package ru.yalymar.Chess.figure;

import org.junit.Test;
import ru.yalymar.Chess.board.Board;
import ru.yalymar.Chess.cell.Cell;
import ru.yalymar.Chess.chessexceptions.ImposibleMoveException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BishopTest {

    Board board = new Board();

    @Test
    public void wayTest() {
        Cell dist = new Cell(3,6,"Black");
        Cell[] result = null;
        try {
            result = board.getFigures()[0].way(dist);
        } catch (ImposibleMoveException e) {
            e.printStackTrace();
        }
        Cell[] expected = new Cell[4];
        expected[0] = new Cell(6, 3, "Black");
        expected[1] = new Cell(5, 4, "Black");
        expected[2] = new Cell(4, 5, "Black");
        expected[3] = new Cell(3, 6, "Black");
        assertThat(result, is(expected));
    }

    @Test
    public void cloneTest() {
        Cell dist = new Cell(3,6,"Black");
        Figure result = null;
        try {
            result = board.getFigures()[0].clone(dist);
        } catch (ImposibleMoveException e) {
            e.printStackTrace();
        }
        Figure expected = new Bishop(new Cell(3,6,"Black"), true);
        assertThat(result, is(expected));
    }

}