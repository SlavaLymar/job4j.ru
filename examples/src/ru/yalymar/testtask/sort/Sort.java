package ru.yalymar.testtask.sort;

import org.apache.log4j.Logger;
import ru.yalymar.testtask.manager.Manager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Sort {

    public static final Logger logger = Logger.getLogger(Sort.class);
    private final ExecutorService threadPool;
    private Properties properties = new Properties();

    public Sort() {
        this.initProperties();
        this.threadPool = Executors.newFixedThreadPool(
                Integer.parseInt(this.properties.getProperty("COUNTOFTHREADS")));
    }

    private void initProperties(){
        Class c = this.getClass();
        InputStream inputStream = c.getResourceAsStream("/settings.properties");
        try {
            this.properties.load(inputStream);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void sort() {
        Manager manager = new Manager(properties, threadPool);
        manager.readFile();
    }

    public ExecutorService getThreadPool() {
        return this.threadPool;
    }
}
