package ru.yalymar.orderbook.parser;

import ru.yalymar.orderbook.models.BookContainer;
import ru.yalymar.orderbook.models.Order;
import ru.yalymar.orderbook.models.OrderBook;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLParser {

    private String buffer = "";
    private BookContainer bookContainer;
    private final Pattern PATTERN = Pattern.compile("(?<=\\b=\")[^\"]*");

    public XMLParser(BookContainer bookContainer) {
        this.bookContainer = bookContainer;
    }

    public void parse(File input) {
        try(FileInputStream in =
                    new FileInputStream(input);
        //    FileWriter out = new FileWriter(output)
        ){

            long s = input.length();
            while (s > 0) {
                byte[] buffer = new byte[1024];
                int i = in.read(buffer);
                ByteBuffer buf = ByteBuffer.wrap(buffer);
                CharBuffer charbuf = Charset.forName("Cp866").decode(buf);
                char[] ch_array = charbuf.array();
                this.workWithStrings(new String(ch_array));


                //out.write(ch_array);

                s -= i;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

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
            this.workWithString(str);
            i--;
        }
    }

    public List<String> workWithString(String str) {
        List<String> list = new ArrayList<>();
        Matcher matcher = PATTERN.matcher(str);
        while(matcher.find()){
            list.add(matcher.group());
        }

        if(list.size() > 0) {
            String bookName = this.getBookName(list.get(0));
            OrderBook ob = this.hasBook(bookName);
            this.getAction(list, ob);
            return list;
        }
        return list;
    }

    private String getBookName(String string) {
        string = string.trim();
        return string;
    }

    private void getAction(List<String> list, OrderBook ob) {
        if(list.size() == 5) {
            Order o = new Order(ob, this.getOrderID(list.get(4)),
                    this.getOperation(list.get(1)), this.getVolume(list.get(3)),
                    this.getPrice(list.get(2)));
            ob.addOrder(o);
        }
        if(list.size() == 2) ob.removeOrder(this.getOrderID(list.get(1)));
    }

    private float getPrice(String string) {
        return Float.valueOf(string.trim());
    }

    private short getVolume(String string) {
        return Short.valueOf(string.trim());
    }

    private String getOperation(String string) {
        return string.trim();
    }

    private int getOrderID(String string) {
        return Integer.parseInt(string.trim());
    }

    private OrderBook hasBook(String string) {
        OrderBook result;
        for(OrderBook ob : this.bookContainer.getList()){
            if(ob.getName().equals(string)) {
                result = ob;
                return result;
            }
        }
        result = new OrderBook(string);
        this.bookContainer.getList().add(result);
        return result;
    }

}
