package ru.yalymar.jdbcconnectionpool;

import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;

public class JDBCConnectionPoolTest {

    @Test
    public void whenCreateCPoolShouldGetInt() throws InterruptedException {
        JDBCConnectionPool cPool = new JDBCConnectionPool();
        Task task1 = new Task("SELECT * FROM car;", true);
//        Task task2 = new Task("INSERT INTO car (name, body_id, engine_id, transmission_id) " +
//                "values('Lada Baklazhan', 1, 1, 1);", false);
//        Task task3 = new Task("SELECT * FROM car WHERE id = 7", true);
//        Task task4 = new Task("DELETE FROM car WHERE id = 7", false);
//        Task task5 = new Task("SELECT * FROM car", true);
//        Task task6 = new Task("SELECT * FROM car WHERE body_id = 3", true);

        cPool.add(task1);
//        cPool.add(task2);
//        cPool.add(task3);
//        cPool.add(task4);
//        cPool.add(task5);
//        cPool.add(task6);

        try {
            assertNotNull(task1.getResult().getRs());
//            assertNotNull(task2.getResult().getResult());
//            assertNotNull(task3.getResult().getRs());
//            assertNotNull(task4.getResult().getResult());
//            assertNotNull(task5.getResult().getRs());
//            assertNotNull(task6.getResult().getRs());
        }
        finally {
            // waiting for complete all threads
            Thread.sleep(1000);
            cPool.stop();
        }
    }
}