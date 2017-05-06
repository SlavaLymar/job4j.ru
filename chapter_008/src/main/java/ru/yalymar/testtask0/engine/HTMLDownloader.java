package ru.yalymar.testtask0.engine;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

/**
 * @author slavalymar
 * @since 06.05.2017
 * @version 1
 */
public class HTMLDownloader {

    private Document document;
    private static final Logger logger = Logger.getLogger(HTMLDownloader.class);

    public Document getDocument() {
        return this.document;
    }

    /** download html. Ten attempts are given
     * @param url
     * @return Document
     */
    public Document download(String url){
        for (int i = 0; i < 10; i++) {
            try {
                return this.document = Jsoup.connect(url).get();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return null;
    }
}
