package ru.yalymar.checkbyte;

import org.junit.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author slavalymar
 * @since 25.01.2017
 * @version 1
 */
public class CheckByteTest {

    @Test
    public void isNumber() {
        InputStream in = null;
        try {
            in = new FileInputStream("C:\\Java\\junior\\chapter_003\\resources\\Task1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CheckByte checkByte = new CheckByte();
        boolean result = checkByte.isNumber(in);
        assertThat(result, is(true));
    }

}