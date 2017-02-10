package ru.yalymar.testtask;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

class SearchTest {

    @Test
    public void whenEnterKeysThenWeCatchThemTest() {
        Search search = new Search();
        search.fillKeys("-d C:/Java -n 1.txt -m -o log.txt".split(" "));
        String[] expected = new String[7];
        expected[0] = "-d";
        expected[1] = "C:/Java";
        expected[2] = "-n";
        expected[3] = "1.txt";
        expected[4] = "-m";
        expected[5] = "-o";
        expected[6] = "log.txt";
        assertArrayEquals(search.getKeys(), expected);
    }

    @Test
    public void fillParamTest(){
        Search search = new Search();
        search.fillKeys("-d C:/Java -n 1.txt -m -o log.txt".split(" "));
        search.fillParam();
        String[] result = {search.getDirectory(), search.getFileName(), search.getLogTXT()};
        String[] expected = {"C:/Java", "1.txt", "log.txt"};
        assertArrayEquals(result, expected);

    }
}