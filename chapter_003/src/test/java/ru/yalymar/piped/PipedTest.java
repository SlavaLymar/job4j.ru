package ru.yalymar.piped;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PipedTest {

    @Test
    public void pipedTest(){
        Piped piped = new Piped();
        piped.init();
        assertThat( piped.getCountRead(),is(100));
    }
}