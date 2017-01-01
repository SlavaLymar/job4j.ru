package ru.maxfromthird;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author slavalymar
 * @since 01.01.2017
 * @version  1
 */
public class MaxFromThirdTest {

    @Test
    public void maxThirdTest() {
        int x = 10, y = 20, z = 30;
        MaxFromThird mft = new MaxFromThird();
        assertThat(mft.maxThird(x, y, z), is(30));
    }

}