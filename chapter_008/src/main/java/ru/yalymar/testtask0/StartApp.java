package ru.yalymar.testtask0;

import org.apache.log4j.Logger;
import ru.yalymar.jdbc.start.Input;
import ru.yalymar.testtask0.engine.Analyzer;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * @author slavalymar
 * @since 06.05.2017
 * @version 1
 */
public class StartApp {

    private static final Logger logger = Logger.getLogger(StartApp.class);
    private Properties properties = new Properties();
    private Analyzer analyzer;

    /** return property`s instance for testing
     * @return Properties
     */
    public Properties getProperties() {
        return this.properties;
    }

    /**
     * initialized
     */
    public void init(){
        this.initProperties();
        this.analyzer = new Analyzer();
        this.startApp();
    }

    /**
     * start app. After work app will be sleeping 24 hours
     */
    private void startApp() {
        while(true){
            this.mainLoop();
            try {
                System.out.println("App falls asleep.");
                Thread.sleep(86400000);
            } catch (InterruptedException e) {
                logger.error("App was stopped!");
                break;
            }
        }
    }

    /**
     * analyze pages of forum
     */
    private void mainLoop() {
        int pageCounter = 1;
        boolean stop;
        do {
            stop = this.analyzer.analyze(String.format("%s%s%s",
                    this.properties.getProperty("url"), "/", pageCounter++));
        }
        while(stop);
    }

    /**
     * initialized prorepties
     */
    public void initProperties(){
        Class c = this.getClass();
        InputStream inputStream = c.getResourceAsStream("/a.properties");
        try {
            this.properties.load(inputStream);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /** main method
     * @param args
     */
    public static void main(String[] args) {
        new StartApp().init();
    }
}
