package ru.yalymar.polyndrom;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author slavalymar
 * @since 29.01.2017
 * @version 1
 */
public class IsPolyndromeTest {

    @Test
    public void isPolyndrcome() {
        IsPolyndrome isP = new IsPolyndrome();
        String text = "RotOr";
        boolean result = false;
        try {
            result = isP.isPolyndrome(text);
        } catch (NotFiveException e) {

        }
        assertThat(result, is(true));
    }

}