package ru.yalymar.menu;

import org.junit.Test;
import ru.yalymar.action.UserAction;
import ru.yalymar.start.ConsoleInput;
import ru.yalymar.tracker.Tracker;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author slavalymar
 * @since 13.01.2017
 * @version 1
 */
public class MenuTest {
    @Test
    public void getIntArrTest() {
        Menu menu = new Menu(new ConsoleInput(), new Tracker());
        UserAction[] arr = new UserAction[6];
        menu.fillMenu();
        int[] result = menu.getIntArr();
        int[] expected = new int[]{0, 1, 2, 3, 4, 5};
        assertThat(result, is(expected));
    }

}