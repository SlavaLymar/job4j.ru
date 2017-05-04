package ru.yalymar.testtask0.engine;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.yalymar.testtask0.db.DBManager;
import ru.yalymar.testtask0.models.Month;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyzer {

    private HTMLDownloader htmlDownloader = new HTMLDownloader();
    private DBManager dbManager = new DBManager();
    private static final Logger logger = Logger.getLogger(Analyzer.class);
    private Properties properties = new Properties();

    public Analyzer() {
        this.initProperties();
    }

    private void initProperties() {
        try(FileInputStream in = new FileInputStream(
                "C:/Java/job4j.ru/chapter_008/resources/a.properties")){

            this.properties.load(in);
        }
        catch (IOException e){
            logger.error(e.getMessage(), e);
        }
    }

    public Document getHTML(String url){
        return this.htmlDownloader.download(url);
    }

    public void analyze(){
        boolean stop = false;
        int pageCounter = 1;

        do{
            Document doc = this.getHTML(String.format("%s%s%s", this.properties.getProperty("url"), "/", pageCounter++));
            Elements elementsRow = doc.select("tr");
            for(Element eRow : elementsRow){
                if (this.isActual(eRow)
                        //&& this.isJAVA(eRow)
                        ) {

                }
                else stop = true;
            }


        }
        while (!stop);
    }


    public boolean isActual(Element e){
        Elements elements = e.getElementsByClass("altCol");
        if(elements.size() == 2) {
            Calendar calendar = this.createCorrectDate(elements.get(1).text());

        }
        return false;
    }

    public Calendar createCorrectDate(String text) {
        Calendar result = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        Pattern todayYesterdayPattern = Pattern.compile("[0-9]+");
        Matcher todayYesterdayMatcher = todayYesterdayPattern.matcher(text);
        int i = 0;
        if(text.contains("сегодня")) {
            int[] arr = new int[2];
            while (todayYesterdayMatcher.find()){
                arr[i++] = Integer.parseInt(todayYesterdayMatcher.group(0));
            }
            result.set(result.get(Calendar.YEAR), result.get(Calendar.MONTH), result.get(Calendar.DAY_OF_MONTH), arr[0], arr[1]);
            return result;
        }
        else if(text.contains("вчера")){
            int[] arr = new int[2];
            while (todayYesterdayMatcher.find()){
                arr[i++] = Integer.parseInt(todayYesterdayMatcher.group(0));
            }
            result.set(result.get(Calendar.YEAR), result.get(Calendar.MONTH), result.get(Calendar.DAY_OF_MONTH), arr[0], arr[1]);
            result.add(Calendar.DAY_OF_MONTH, -1);
            return result;
        }
        else {
            int[] arr = new int[4];
            while (todayYesterdayMatcher.find()){
                arr[i++] = Integer.parseInt(todayYesterdayMatcher.group(0));
            }
            result.set(arr[1] + 2000, this.getMonth(text), arr[0], arr[2], arr[3]);
            result.add(Calendar.MONTH, 1);
            return result;
        }
    }

    public int getMonth(String text) {
        Pattern pattern = Pattern.compile("\\W{3}\\s");
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()){
            String str = matcher.group(0);
            Month[] months = Month.values();
            for(int i = 0; i<months.length; i++){
                if(months[i].toString().equals(str.trim())){
                    return i;
                }
            }
        }
        return 0;
    }


}
