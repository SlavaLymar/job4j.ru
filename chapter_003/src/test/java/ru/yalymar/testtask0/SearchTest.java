package ru.yalymar.testtask0;

import org.junit.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class SearchTest {

    @Test
    public void fillKeysTest() {
        Search search = new Search();
        search.fillKeys("-d C:/Java -n 1.txt -f -o log.txt".split(" "));
        String[] expected = new String[7];
        expected[0] = "-d";
        expected[1] = "C:/Java";
        expected[2] = "-n";
        expected[3] = "1.txt";
        expected[4] = "-f";
        expected[5] = "-o";
        expected[6] = "log.txt";
        assertArrayEquals(search.getKeys(), expected);
    }

    @Test
    public void fillParamTest(){
        Search search = new Search();
        search.fillKeys("-d C:/Java -n 1.txt -f -o log.txt".split(" "));
        search.fillParam();
        String[] result = {search.getDirectory(), search.getFileName(), search.getLogTXT()};
        String[] expected = {"C:/Java", "1.txt", "log.txt"};
        assertArrayEquals(result, expected);
    }

    @Test
    public void searchFullNameTest() {
        Search search = new Search();
        search.fillKeys("-d C:/Java/junior/examples -n 1.txt -f -o log.txt".split(" "));
        search.fillParam();

        String absolutePathLogTXT = String.format
                ("%s%s%s", search.getDirectory(), "/", search.getLogTXT());
        try(FileWriter fw = new FileWriter(absolutePathLogTXT);
            BufferedReader in = new BufferedReader(new FileReader(absolutePathLogTXT))) {
            search.search(new File(search.getDirectory()), fw);
            String expected = "C:\\Java\\junior\\examples\\resources\\1.txt was found!";
            List<String> listOfLog = new ArrayList<>();
            do {
                listOfLog.add(in.readLine());
            }
            while(in.ready());

            boolean result = false;
            for(String str: listOfLog){
                if(str.equals(expected)) {
                    result = true;
                }
            }
            assertThat(result, is(true));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void searchByMaskTest() {
        Search search = new Search();
        search.fillKeys("-d C:/Java/junior/examples -n *.txt -m -o log.txt".split(" "));
        search.fillParam();

        String absolutePathLogTXT = String.format
                ("%s%s%s", search.getDirectory(), "/", search.getLogTXT());
        try(FileWriter fw = new FileWriter(absolutePathLogTXT);
            BufferedReader in = new BufferedReader(new FileReader(absolutePathLogTXT))) {
            search.search(new File(search.getDirectory()), fw);
            String expected = "C:\\Java\\junior\\examples\\resources\\1.txt was found with *.txt mask";
            List<String> listOfLog = new ArrayList<>();
            do {
                listOfLog.add(in.readLine());
            }
            while(in.ready());

            boolean result = false;
            for(String str: listOfLog){
                if(str.equals(expected)) {
                    result = true;
                }
            }
            assertThat(result, is(true));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void searchByRegexTest() {
        Search search = new Search();
        search.fillKeys("-d C:/Java/junior/examples -n [0-9] -r -o log.txt".split(" "));
        search.fillParam();

        String absolutePathLogTXT = String.format
                ("%s%s%s", search.getDirectory(), "/", search.getLogTXT());
        try(FileWriter fw = new FileWriter(absolutePathLogTXT);
            BufferedReader in = new BufferedReader(new FileReader(absolutePathLogTXT))) {
            search.search(new File(search.getDirectory()), fw);
            String expected = "C:\\Java\\junior\\examples\\resources\\1.txt was found with *.txt mask";
            List<String> listOfLog = new ArrayList<>();
            do {
                listOfLog.add(in.readLine());
            }
            while(in.ready());

            boolean result = false;
            for(String str: listOfLog){
                if(str.equals(expected)) {
                    result = true;
                }
            }
            //assertThat(result, is(true));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}