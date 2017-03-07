package ru.lymar.isp.input;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import ru.lymar.isp.menu.Menu;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class ConsoleInputTest {

    @Rule
    public final TextFromStandardInputStream systemInMock
            = emptyStandardInputStream();

    @Test
    public void askTest(){

        systemInMock.provideLines("1.1", "3");
        ConsoleInput consoleInput = new ConsoleInput();
        Menu menu = new Menu(consoleInput);
        menu.fillMenu();
        String result = consoleInput.ask("question", menu.getKeysArr(menu.getUserActions()));
        assertThat(result, is("1.1"));

    }

}