package ru.yalymar.testtask0;

import org.apache.log4j.Logger;
import ru.yalymar.testtask0.engine.Analyzer;
import java.io.FileInputStream;
import java.io.IOException;
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
    private void initProperties() {
        try(FileInputStream in = new FileInputStream(
                "C:/Java/job4j.ru/chapter_008/resources/a.properties")){

            this.properties.load(in);
        }
        catch (IOException e){
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
