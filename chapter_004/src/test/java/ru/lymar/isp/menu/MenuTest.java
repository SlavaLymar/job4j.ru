package ru.lymar.isp.menu;

import org.junit.Test;
import ru.lymar.isp.input.Input;
import ru.lymar.isp.input.ValidateInput;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MenuTest {

    @Test
    public void getKeysArrTest(){
        Input input = new ValidateInput();
        Menu menu = new Menu(input);
        menu.fillMenu();
        assertThat(menu.getKeysArr(menu.getUserActions()).get(1), is("2"));
    }



}