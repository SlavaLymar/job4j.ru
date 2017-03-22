package ru.yalymar.orderbook.parser;

import org.junit.Test;
import ru.yalymar.orderbook.models.BookContainer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class XMLParserTest {

    @Test
    public void whenParseXMLFile(){
        BookContainer bookContainer = new BookContainer();
        XMLParser parser = new XMLParser(bookContainer);

        double start = System.currentTimeMillis();
        parser.parse(new File("C:/Java/job4j.ru/chapter_005/resources/orders.xml"));
        double finish = System.currentTimeMillis();
        System.out.println((finish-start)/1000.0+" s");
    }

    @Test
    public void whenWorkWithStringShouldGetList(){
        BookContainer bookContainer = new BookContainer();
        XMLParser parser = new XMLParser(bookContainer);
        List<String> result;

        String s1 = "<AddOrder book=\"book-3\" operation=\"BUY\" price=\" 99.50\" volume=\"86\" orderId=\"2\" />";
        String s2 = "<DeleteOrder book=\"book-1\" orderId=\"3750\" />";

        result = parser.workWithString(s1);
        assertThat(result.size(), is(5));

        result = parser.workWithString(s2);
        assertThat(result.size(), is(2));
    }
}