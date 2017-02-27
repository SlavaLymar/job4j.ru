package ru.lymar.testtask.actions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import ru.lymar.testtask.exception.OutOfRangeException;
import ru.lymar.testtask.player.RealPlayer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class InputTest {

    @Rule
    public final TextFromStandardInputStream systemInMock
            = emptyStandardInputStream();

    @Test
    public void getNumberActual() throws OutOfRangeException {
        systemInMock.provideLines("2", "1");
        RealPlayer realPlayer = new RealPlayer("X", "RealPlayer", 3);
        int x = realPlayer.getNumber("Enter X: ");
        int y = realPlayer.getNumber("Enter Y: ");
        assertThat(x, is(2));
        assertThat(y, is(1));
    }


}