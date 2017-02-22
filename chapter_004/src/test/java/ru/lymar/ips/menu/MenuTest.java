package ru.lymar.ips.menu;

import org.junit.Test;
import ru.lymar.ips.input.Input;
import ru.lymar.ips.input.ValidateInput;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MenuTest {

    @Test
    public void getKeysArrTest(){
        Input input = new ValidateInput();
        Menu menu = new Menu(input);
        menu.fillMenu();
        assertThat(menu.getKeysArr().get(1), is("1.1"));
    }



}