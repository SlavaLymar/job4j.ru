package ru.yalymar.checkbyte;

import org.junit.Test;
import java.io.ByteArrayInputStream;
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
        byte[] b = new byte[]{1,2,3,4,5,6,7,8,9,10};
        InputStream in = new ByteArrayInputStream(b);
        CheckByte checkByte = new CheckByte();
        boolean result = checkByte.isNumber(in);
        assertThat(result, is(true));
    }

}