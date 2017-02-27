package ru.lymar.testtask.field;

import org.junit.Test;
import ru.lymar.testtask.exception.CellIsOccupiedException;
import ru.lymar.testtask.exception.OutOfRangeException;
import ru.lymar.testtask.player.Player;
import ru.lymar.testtask.player.RealPlayer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class FieldTest {

    @Test
    public void whenFillFild(){
        GameField gm = new GameField(3);
        gm.fillFields();
        Cell expected = new Cell(0, 0, ".");
        assertThat(gm.getCells()[0][0].getValue(), is(expected.getValue()));
    }

    @Test
    public void whenCellIsNotOccupied(){
        GameField gm = new GameField(3);
        gm.fillFields();
        boolean result = gm.isFreeCell(0,1);
        assertEquals(result, true);
    }

    @Test
    public void whenPlayerTurnToNotOccupiedCell() throws CellIsOccupiedException, OutOfRangeException {
        GameField gm = new GameField(3);
        gm.fillFields();
        Player player = new RealPlayer("X", "RealPlayer", 3);
        gm.move(0,0, player);
        assertThat(gm.getCells()[0][0].getValue(), is(player.getFlag()));
    }

    @Test(expected = CellIsOccupiedException.class)
    public void whenPlayerTurnToOccupiedCell() throws CellIsOccupiedException, OutOfRangeException {
        GameField gm = new GameField(3);
        gm.fillFields();
        Player player = new RealPlayer("X", "RealPlayer",3 );
        gm.move(0,0, player);
        gm.move(0,0, player);
    }

    @Test
    public void whenLineWasFillingThenShouldWin() throws CellIsOccupiedException, OutOfRangeException {
        GameField gm = new GameField(3);
        gm.fillFields();
        Player player = new RealPlayer("X", "RealPlayer",3);
        gm.move(0,0, player);
        gm.move(0,1, player);
        gm.move(0,2, player);
        boolean result = gm.winCheckedLine(player);
        assertEquals(true, result);
    }

    @Test
    public void whenDiagonalWasFillingThenShouldWin() throws CellIsOccupiedException, OutOfRangeException {
        GameField gm = new GameField(3);
        gm.fillFields();
        Player player = new RealPlayer("X", "RealPlayer", 3);
        gm.move(0,0, player);
        gm.move(1,1, player);
        gm.move(2,2, player);
        boolean result = gm.winCheckedDiagonal(player);
        assertEquals(true, result);
    }

    @Test
    public void whenWinCheked() throws CellIsOccupiedException, OutOfRangeException {
        GameField gm = new GameField(3);
        gm.fillFields();
        Player player = new RealPlayer("X", "RealPlayer", 3);
        gm.move(0,0, player);
        gm.move(1,1, player);
        gm.move(2,2, player);
        boolean result = gm.winChecked(player);
        assertEquals(true, result);
    }


}