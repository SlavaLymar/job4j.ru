package ru.yalymar.orderbook.parser;

import ru.yalymar.orderbook.models.Book;
import ru.yalymar.orderbook.models.BookContainer;
import ru.yalymar.orderbook.models.Order;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author slavalymar
 * @since 23.03.2017
 * @version 1
 */
public class XMLParser {

    /**
     * buffer
     */
    private String buffer = "";

    /**
     * instance of BookContainer
     */
    private BookContainer bookContainer;

    /**
     * pattern for finding values into XML
     */
    private final Pattern PATTERN = Pattern.compile("(?<=\\b=\")[^\"]*");

    public XMLParser(BookContainer bookContainer) {
        this.bookContainer = bookContainer;
    }

    /** read XML
     * @param input
     */
    public void parse(File input) {
        try(FileInputStream in =
                    new FileInputStream(input)){

            long s = input.length();
            while (s > 0) {

                byte[] buffer = new byte[1024*1024*5];
                int i = in.read(buffer);
                ByteBuffer buf = ByteBuffer.wrap(buffer);
                CharBuffer charbuf = Charset.forName("Cp866").decode(buf);
                char[] ch_array = charbuf.array();

                // start work with piece of XML file
                this.workWithStrings(new String(ch_array));

                s -= i;

            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /** split piece of XML file to Strings
     * @param s
     */
    private void workWithStrings(String s) {
        s = String.format("%s%s", this.buffer, s);
        StringTokenizer st = new StringTokenizer(s, "/>");
        int i = st.countTokens();
        while(i > 0){
            String str = st.nextToken();
            if(str.contains("<?xml version") || str.contains("<Ord")) {
                i--;
                continue;
            }
            if(i == 1) {
                if(!str.contains("/>")) {
                    this.buffer = str;
                    break;
                }
                else {
                    this.buffer = "";
                    break;
                }
            }

            // start work with string
            this.workWithString(str);
            i--;
        }
    }

    /** create list of values of string
     * @param str
     * @return List
     */
    public List<String> workWithString(String str) {
        List<String> list = new ArrayList<>();
        Matcher matcher = PATTERN.matcher(str);
        while(matcher.find()){
            list.add(matcher.group());
        }

        if(list.size() > 0) {
            String bookName = this.getBookName(list.get(0));
            Book ob = this.hasBook(bookName);
            this.getAction(list, ob);
            return list;
        }
        return list;
    }

    /** choose action. Add or Delete
     * @param list
     * @param ob
     */
    private void getAction(List<String> list, Book ob) {
        if(list.size() == 5) {
            Order o = new Order(this.getOrderID(list.get(4)),
                    this.getOperation(list.get(1)), this.getVolume(list.get(3)),
                    this.getPrice(list.get(2)));
            ob.addOrder(o);
        }
        if(list.size() == 2) {
            ob.removeOrder(this.getOrderID(list.get(1)));
        }
    }

    /** get book`s name
     * @param string
     * @return String
     */
    private String getBookName(String string) {
        return string.trim();
    }

    /** return price
     * @param string
     * @return float
     */
    private float getPrice(String string) {
        return Float.valueOf(string.trim());
    }

    /** return volume
     * @param string
     * @return int
     */
    private int getVolume(String string) {
        return Short.valueOf(string.trim());
    }

    /** return type of operation
     * @param string
     * @return String
     */
    private String getOperation(String string) {
        return string.trim();
    }

    /** return id
     * @param string
     * @return int
     */
    private int getOrderID(String string) {
        return Integer.parseInt(string.trim());
    }

    /** select a book or create new one
     * @param string
     * @return Book
     */
    private Book hasBook(String string) {
        Book result;
        for(Book ob : this.bookContainer.getList()){
            if(ob.getName().equals(string)) {
                result = ob;
                return result;
            }
        }
        result = new Book(string);
        this.bookContainer.getList().add(result);
        return result;
    }

}
