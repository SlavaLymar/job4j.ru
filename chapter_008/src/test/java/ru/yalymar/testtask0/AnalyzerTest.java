package ru.yalymar.testtask0;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import ru.yalymar.testtask0.engine.Analyzer;

import java.util.Calendar;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;

public class AnalyzerTest {

    @Test
    public void whenGetMonthShouldGetIt(){
        Analyzer analyzer = new Analyzer();
        int result = analyzer.getMonth("29 апр 17, 15:57");
        assertThat(result, is(3));
    }

    @Test
    public void whenCreateDateShouldGetCalendar(){
        Analyzer analyzer = new Analyzer();
        Calendar result1 = analyzer.createCorrectDate("сегодня, 10:50");
        Calendar result2 = analyzer.createCorrectDate("вчера, 09:58");
        Calendar result3 = analyzer.createCorrectDate("29 апр 17, 15:57");

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH),
                calendar1.get(Calendar.DAY_OF_MONTH), 10, 50);
        result1.set(Calendar.MILLISECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        assertThat(result1, is(calendar1));

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH),
                calendar2.get(Calendar.DAY_OF_MONTH), 9, 58);
        calendar2.add(Calendar.DAY_OF_MONTH, -1);
        result2.set(Calendar.MILLISECOND, 0);
        calendar2.set(Calendar.MILLISECOND, 0);
        assertThat(result2, is(calendar2));

        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2017, 3, 29, 15, 57);
        result3.set(Calendar.SECOND, 0);
        calendar3.set(Calendar.SECOND, 0);
        result3.set(Calendar.MILLISECOND, 0);
        calendar3.set(Calendar.MILLISECOND, 0);
        assertThat(result3, is(calendar3));
    }

    @Test
    public void whenGetCloseTopicShouldGetFalse(){
        Analyzer analyzer = new Analyzer();
        Document document = analyzer.getHTML("http://www.sql.ru/forum/job-offers");
        Elements elementsRow = document.select("tr");
        int i = 0;
        boolean result = false;
        for(Element e : elementsRow){
            i++;
            if(i == 5){
                result = analyzer.isJAVA(e);
            }
        }
        assertFalse(result);
    }

    @Test
    public void whenCompareDatesShouldGetBoolean(){
        Analyzer analyzer = new Analyzer();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -2);
        boolean result = analyzer.getDifferenceDate(calendar);
        assertFalse(result);

        calendar.add(Calendar.YEAR, 1);
        calendar.add(Calendar.MONTH, 6);
        boolean result1 = analyzer.getDifferenceDate(calendar);
        assertTrue(result1);
    }

    @Test
    public void whenAnalyzeStringShouldGetBoolean(){
        Analyzer analyzer = new Analyzer();
        String origin1 = "Ищем мегакрутого java прогера";
        String origin2 = "Ищем неочень программиста JAVASCRIPT";
        String origin3 = "Хитрый HR написал раздельно JaVa scRipt";
        String origin4 = "Хитрый HR JaVa/C#/javascRipt";
        String origin5 = "Java Хитрый HR JaVa/C#/javascRipt";

        boolean result1 = analyzer.isReallyJAVA(origin1);
        boolean result2 = analyzer.isReallyJAVA(origin2);
        boolean result3 = analyzer.isReallyJAVA(origin3);
        boolean result4 = analyzer.isReallyJAVA(origin4);
        boolean result5 = analyzer.isReallyJAVA(origin5);

        assertTrue(result1);
        assertFalse(result2);
        assertFalse(result3);
        assertTrue(result4);
        assertTrue(result5);

    }

}