package ru.yalymar.trend;

import org.junit.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class TrendGeneratorTest {

    public Task getTask1(){
        Task task1 = new Task("task1");
        Calendar task1Cal1 = Calendar.getInstance();
        task1Cal1.add(Calendar.HOUR, 2);
        Calendar task1Cal2 = Calendar.getInstance();
        task1Cal2.add(Calendar.DAY_OF_YEAR, 7);
        Calendar task1Cal3 = Calendar.getInstance();
        task1Cal3.add(Calendar.MONTH, 6);
        task1.addOperation(new Operation("Wash dishes", task1Cal1));
        task1.addOperation(new Operation("Go to shop", task1Cal2));
        task1.addOperation(new Operation("Be cool", task1Cal3));
        return task1;
    }

    public Task getTask2() {
        Task task2 = new Task("task2");
        Calendar task2Cal1 = Calendar.getInstance();
        task2Cal1.add(Calendar.HOUR, 7);
        Calendar task2Cal2 = Calendar.getInstance();
        task2Cal2.add(Calendar.DAY_OF_YEAR, 20);
        Calendar task2Cal3 = Calendar.getInstance();
        task2Cal3.add(Calendar.MONTH, 3);
        task2.addOperation(new Operation("Working", task2Cal1));
        task2.addOperation(new Operation("Repair bike", task2Cal2));
        task2.addOperation(new Operation("Get job", task2Cal3));
        return task2;
    }

    @Test
    public void whenGenerateTrendShouldGetTable(){
        TrendGenerator trendGenerator =
                new TrendGenerator(new File("C:/Java/job4j.ru/chapter_005/resources/trend.txt"));
        Calendar start = Calendar.getInstance();

        List<Task> list = new ArrayList<>(Arrays.asList(this.getTask1(), this.getTask2()));

        Calendar finish = Calendar.getInstance();
        finish.add(Calendar.YEAR, 1);

        boolean result = trendGenerator.generate(list, start, finish);
        assertTrue(result);
    }
}