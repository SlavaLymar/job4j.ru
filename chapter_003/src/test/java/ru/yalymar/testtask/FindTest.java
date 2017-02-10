package ru.yalymar.testtask;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindTest {

    @Test
    public void validateTest() throws NotValidateException {
        Find find = new Find();
        find.validate("-d C:/Java -n 1.txt -m -o log.txt".split(" "));
        assertThat(find.getIsValidate(), is(true));
    }

}