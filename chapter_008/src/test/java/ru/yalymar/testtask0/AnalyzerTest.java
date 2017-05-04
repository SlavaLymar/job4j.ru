package ru.yalymar.testtask0;

import org.junit.Test;
import ru.yalymar.testtask0.engine.Analyzer;
import java.util.Calendar;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
        calendar3.set(2017, 4, 29, 15, 57);
        result3.set(Calendar.MILLISECOND, 0);
        calendar3.set(Calendar.MILLISECOND, 0);
        assertThat(result3, is(calendar3));
    }
}