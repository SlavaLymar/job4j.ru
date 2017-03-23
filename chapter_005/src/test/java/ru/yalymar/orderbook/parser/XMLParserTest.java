package ru.yalymar.orderbook.parser;

import org.junit.Test;
import ru.yalymar.orderbook.models.BookContainer;
import java.io.File;

public class XMLParserTest {

    @Test
    public void whenParseXMLDemo(){
        BookContainer container = new BookContainer();
        XMLParser parser = new XMLParser(container);

        double start = System.currentTimeMillis();
        parser.parse(new File("C:/Java/job4j.ru/chapter_005/resources/orders.xml"));
        container.getList().get(0).printBook();
        System.out.println(((System.currentTimeMillis()-start)/1000.0)+ " s");
    }
}