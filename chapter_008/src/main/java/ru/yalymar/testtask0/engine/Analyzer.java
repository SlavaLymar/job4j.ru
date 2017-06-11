package ru.yalymar.testtask0.engine;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.yalymar.testtask0.db.DBManager;
import ru.yalymar.testtask0.models.Month;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author slavalymar
 * @since 06.05.2017
 * @version 1
 */
public class Analyzer {

    /**
     * downloader html
     */
    private HTMLDownloader htmlDownloader = new HTMLDownloader();

    /**
     * database dao
     */
    private DBManager dbManager = new DBManager();

    private static final Logger logger = Logger.getLogger(Analyzer.class);

    /**
     * date of topic
     */
    private Calendar dateOfCreate = Calendar.getInstance();

    public Document getHTML(String url){
        return this.htmlDownloader.download(url);
    }

    /** analyze: topic`s actuality depends of date of create,
     * it has duplicate into database, whether the offer is valid
     * @param url
     * @return boolean
     */
    public boolean analyze(String url){
        this.dbManager.connectDB();
        boolean result = true;

        Document doc = this.getHTML(url);
        Elements elementsRow = doc.select("tr");
        for (Element eRow : elementsRow) {
            if (this.isActual(eRow)) {
                if (this.checkDuplicate(eRow)){
                    if (this.isJAVA(eRow)) {
                        Elements elements = eRow.getElementsByClass("postslisttopic");
                        Element element = elements.first().child(0);
                        String ref = element.attr("href");
                        String topic = element.text();
                        Timestamp dateOfCreate = new Timestamp(this.dateOfCreate.getTimeInMillis());
                        this.fillDB(topic, ref, dateOfCreate);
                        result = true;
                    }
                }
                else {
                    result = false;
                    this.fillLogger();
                    break;
                }
            }
            else {
                result = false;
                this.fillLogger();
                break;
            }
        }
        this.dbManager.disconnectDB();
        return result;
    }

    /** it has duplicate into database
     * @param eRow
     * @return boolean
     */
    public boolean checkDuplicate(Element eRow){
        Elements elements = eRow.getElementsByClass("postslisttopic");
        if(elements.size() == 0) {
            return true;
        }
        Element element = elements.first().child(0);
        String topic = element.text();
        PreparedStatement st = null;
        try {
            st = this.dbManager.getC().prepareStatement("SELECT * FROM offers WHERE description = ?");
            st.setString(1, topic);
            return this.dbManager.getGo().go(st).getRow() == 0;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            return true;
        }
        finally {
            if(st != null){
                try {
                    st.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /** fill database
     * @param topic
     * @param ref
     * @param dateOfCreate
     * @return int
     */
    private int fillDB(String topic, String ref, Timestamp dateOfCreate) {
        PreparedStatement st = null;
        try {
            st = this.dbManager.getC().prepareStatement("INSERT INTO offers " +
                    "(description, link, dateOfCreate) values (?, ?, ?)");
            st.setString(1, topic);
            st.setString(2, ref);
            st.setTimestamp(3, dateOfCreate);
            return this.dbManager.getGoUpdate().goUpdate(st);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            return 0;
        }
        finally {
            if(st != null){
                try {
                    st.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * fill log.log by offers
     */
    public void fillLogger(){
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = this.dbManager.getC().prepareStatement("SELECT * FROM offers");
            rs = this.dbManager.getGo().go(st);
            while(rs.next()){
                int id = rs.getInt("id");
                String desc = rs.getString("description");
                String link = rs.getString("link");
                Timestamp dateOfCreate = rs.getTimestamp("dateOfCreate");
                logger.info(String.format("%1$-6d%2$-100s%3$-200s%4$-10s", id, desc, link, dateOfCreate.toString()));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        finally {
            if(st != null){
                try {
                    st.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /** check whether the offer is valid
     * @param eRow
     * @return boolean
     */
    public boolean isJAVA(Element eRow) {
        boolean result = false;
        Elements elements = eRow.getElementsByClass("postslisttopic");
        if(elements.size() != 0) {
            Element element = elements.first();
            String topic = element.text();
            if (topic != null) {
                if(topic.contains("закрыт")){
                    return false;
                }
                else{
                    result = this.isReallyJAVA(topic);
                }
            }
        }
        return result;
    }

    /** check whether the offer is valid by regex
     * @param topic
     * @return boolean
     */
    public boolean isReallyJAVA(String topic) {
        List<String> strings =  Arrays.asList(topic.split("\\s"));
        Pattern pattern = Pattern.compile("(^java$)|(java[^s])");
        boolean[] result = new boolean[]{false};
        for(String s : strings) {
            if(s.toLowerCase().contains("java")){
                Matcher matcher = pattern.matcher(s.toLowerCase());
                if(matcher.find()) {
                    result[0] = !(strings.size() - 1 != strings.indexOf(s) &&
                            "script".equals(strings.get(strings.indexOf(s) + 1).toLowerCase()));
                    break;
                }
            }
        }
        return result[0];
    }


    /** check topic`s actuality depends of date of create
     * @param eRow
     * @return boolean
     */
    public boolean isActual(Element eRow){
        Elements els = eRow.getElementsByClass("postslisttopic");
        if(els.size() != 0) {
            Element element = els.first();
            String topic = element.text();
            if (topic != null) {
                if(topic.toLowerCase().contains("важно")){
                    return true;
                }
            }
        }

        boolean result;
        Elements elements = eRow.getElementsByClass("altCol");
        if(elements.size() == 2) {
            Calendar calendar = this.createCorrectDate(elements.get(1).text());
            result = this.getDifferenceDate(calendar);
        }
        else result = true;
        return result;
    }

    /** get difference of dates
     * @param calendar
     * @return boolean
     */
    public boolean getDifferenceDate(Calendar calendar) {
        Calendar current = Calendar.getInstance();
        long diff = current.getTimeInMillis() - calendar.getTimeInMillis();
        double years = (diff/(31536000000L));
        return years < 1;
    }

    /** create correct date
     * @param text
     * @return Calendar
     */
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
            result.set(result.get(Calendar.YEAR), result.get(Calendar.MONTH),
                    result.get(Calendar.DAY_OF_MONTH), arr[0], arr[1]);
            return this.dateOfCreate = result;
        }
        else if(text.contains("вчера")){
            int[] arr = new int[2];
            while (todayYesterdayMatcher.find()){
                arr[i++] = Integer.parseInt(todayYesterdayMatcher.group(0));
            }

            result.set(result.get(Calendar.YEAR), result.get(Calendar.MONTH),
                    result.get(Calendar.DAY_OF_MONTH), arr[0], arr[1]);
            result.add(Calendar.DAY_OF_MONTH, -2);
            return this.dateOfCreate = result;
        }
        else {
            int[] arr = new int[4];
            while (todayYesterdayMatcher.find()){
                arr[i++] = Integer.parseInt(todayYesterdayMatcher.group(0));
            }
            result.set(arr[1] + 2000, this.getMonth(text), arr[0], arr[2], arr[3]);
            return this.dateOfCreate = result;
        }
    }

    /** translates the Russian name into a number. E.g 'янв' -> 01
     * @param text
     * @return int
     */
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
