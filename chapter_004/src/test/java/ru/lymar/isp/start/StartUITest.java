package ru.lymar.isp.start;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import ru.lymar.isp.input.ValidateInput;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class StartUITest {

    @Rule
    public final TextFromStandardInputStream systemInMock
            = emptyStandardInputStream();

    @Test
    public void AppTest(){
        systemInMock.provideLines("1.1", "Exit");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        StartUI start = new StartUI(new ValidateInput());
        start.init();
        String[] strings = out.toString().split("\r\n");
        assertThat(strings[6], is("You picked \"1.1 Item\""));
    }

}