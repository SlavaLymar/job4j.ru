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
        Customer c6 = new Customer(6);
        Customer c7 = new Customer(7);
        Customer c8 = new Customer(8);
        Customer c9 = new Customer(9);
        Customer c10 = new Customer(10);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        c1.setExit(calendar.getTime());

        calendar.add(Calendar.HOUR_OF_DAY, 1);
        c2.setExit(calendar.getTime());

        calendar.add(Calendar.HOUR_OF_DAY, 1);
        c3.setExit(calendar.getTime());

        calendar.add(Calendar.HOUR_OF_DAY, 1);
        c4.setExit(calendar.getTime());

        calendar.add(Calendar.HOUR_OF_DAY, 1);
        c5.setExit(calendar.getTime());

        Counter counter = new Counter();
        counter.addCustomer(c1);
        counter.addCustomer(c2);
        counter.addCustomer(c3);
        counter.addCustomer(c4);
        counter.addCustomer(c5);

        Counter.Result result = counter.calculateCustomers();
        assertThat(result.countOfCustomers, is(5));
    }

}