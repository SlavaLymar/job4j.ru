package ru.paint;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author slavalymar
 * @since 02.01.2017
 * @version 1
 */
public class PaintTest {
    @Test
    public void pyramidTest() {
        Paint p = new Paint();
        String result = p.pyramid(3);
        assertThat(result, is("  ^ \n ^ ^ \n^ ^ ^ \n"));
    }

}