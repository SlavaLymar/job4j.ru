package ru.lymar.srp.input;

import org.junit.Test;
import java.util.Scanner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class InputTest {

    @Test
    public void readConsole() {
        String str = "20+20";
        Input input = new Input(new Scanner(str));
        String result = input.readConsole();
        assertThat(result, is("20+20"));
    }

}