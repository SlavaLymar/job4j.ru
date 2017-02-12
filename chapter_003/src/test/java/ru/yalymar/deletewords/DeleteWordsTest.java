package ru.yalymar.deletewords;

import org.junit.Test;

import java.io.*;

import static java.lang.System.in;
import static java.lang.System.out;
import static java.lang.System.setOut;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author slavalymar
 * @since 26.01.2017
 * @version 1
 */
public class DeleteWordsTest {

    @Test
    public void dropAbuses() {
        DeleteWords dw = new DeleteWords();
        String inString = "Я надеюсь что эта программа работает правильно,\n " +
                "но если не правильно тогда это просто набор цифр и букв:)";
        String expected = "Я надеюсь что программа работает правильно, но если не правильно тогда просто набор цифр и букв:)";

        byte[] data = inString.getBytes();
        InputStream in = new ByteArrayInputStream(data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String[] abuse = {" это", " эта"};
        dw.dropAbuses(in, System.out, abuse);

        //assertThat(outputStream.toString(), is(expected));
    }

}