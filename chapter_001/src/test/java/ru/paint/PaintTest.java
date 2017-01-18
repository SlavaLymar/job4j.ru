package ru.paint;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author slavalymar
 * @since 18.01.2017
 * @version 1
 */
public class PaintTest {
    @Test
    public void pic() {
        Paint sq = new Square(3);
        Paint tr = new Triangle(3);

        String expected1 = sq.pic();
        String expected2 = tr.pic();

        String result1 = ".  .  .  \n.  .  .  \n.  .  .  \n";
        String result2 = "  . \n . . \n. . . \n";

        assertThat(result1, is(expected1));
        assertThat(result2, is(expected2));
    }

}