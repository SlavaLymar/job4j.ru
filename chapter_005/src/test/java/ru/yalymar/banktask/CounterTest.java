package ru.yalymar.banktask;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CounterTest {

    @Test
    public void whenFindMaximumCustomers(){
        Customer c1 = new Customer(1);
        Customer c2 = new Customer(2);
        Customer c3 = new Customer(3);
        Customer c4 = new Customer(4);
        Customer c5 = new Customer(5);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.HOUR_OF_DAY, 1);
        c1.setExit(calendar1.getTime());

        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.HOUR_OF_DAY, 1);
        c2.setExit(calendar2.getTime());

        Calendar calendar3 = Calendar.getInstance();
        calendar3.add(Calendar.HOUR_OF_DAY, 1);
        c3.setExit(calendar3.getTime());

        Calendar calendar4 = Calendar.getInstance();
        calendar4.add(Calendar.HOUR_OF_DAY, 1);
        c4.setExit(calendar4.getTime());

        Calendar calendar5 = Calendar.getInstance();
        calendar5.add(Calendar.HOUR_OF_DAY, 1);
        c5.setExit(calendar5.getTime());

        Counter counter = new Counter();
        counter.addCustomer(c1);
        counter.addCustomer(c2);
        counter.addCustomer(c3);
        counter.addCustomer(c4);
        counter.addCustomer(c5);

        Counter.Result result = counter.calculateCustomers();
        assertThat(result.countOfCustomers, is(3));
    }

}