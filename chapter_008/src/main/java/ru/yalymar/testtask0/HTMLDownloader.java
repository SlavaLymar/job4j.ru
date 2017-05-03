package ru.yalymar.testtask0;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HTMLDownloader {

    private Properties properties;
    private static final Logger logger = Logger.getLogger(HTMLDownloader.class);

    public HTMLDownloader() {
        this.initProperties();
    }

    private void initProperties() {
        try(FileInputStream in = new FileInputStream(
                "C:/Java/job4j.ru/chapter_008/resources/analyzehtml.properties")){

            this.properties.load(in);
        }
        catch (IOException e){
            logger.error(e.getMessage(), e);
        }
    }

    public Document download(){
        for (int i = 0; i < 10; i++) {
            try {
                return Jsoup.connect(this.properties.getProperty("url")).get();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return null;
    }
}
