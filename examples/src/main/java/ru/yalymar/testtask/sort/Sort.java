package ru.yalymar.testtask.sort;

import org.apache.log4j.Logger;
import ru.yalymar.testtask.manager.Manager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author slavalymar
 * @since 17.07.2017
 * @version 1
 */
public class Sort {

    /**
     * logger
     */
    public static final Logger logger = Logger.getLogger(Sort.class);

    /**
     * instance of thread pool
     */
    private final ExecutorService threadPool;

    /**
     * properties of "resources/settings.properties"
     */
    private Properties properties = new Properties();

    public Sort() {
        this.initProperties();
        this.threadPool = Executors.newFixedThreadPool(
                Integer.parseInt(this.properties.getProperty("COUNTOFTHREADS")));
    }

    /**
     * initialized of properties
     */
    private void initProperties(){
        Class c = this.getClass();
        InputStream inputStream = c.getResourceAsStream("/settings.properties");
        try {
            this.properties.load(inputStream);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * start sorting
     */
    public void sort() {
        Manager manager = new Manager(properties, threadPool);
        manager.managerOfApp();
    }

    public ExecutorService getThreadPool() {
        return this.threadPool;
    }
}
