package ru.yalymar.testtask;

import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author slavalymar
 * @since 12.02.2017
 * @version 1
 */
public class Find {

    // [-d, C:/Java, -n, 1.txt, -r,f,m, -o, log.txt]
    private final String REGEX = ".-d,\\s.+,\\s-n,.+,\\s-.+,\\s-o,\\s.+.";

    /** check keys
     * @param args
     */
    public boolean validate(String[] args){

        boolean result = false;
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(Arrays.deepToString(args));
        if(matcher.find()){
            result = true;
        }
        return result;
    }

    /** start program
     * @param args
     */
    public void init(String[] args) {

        Search search = new Search();
        if(this.validate(args)) {
            search.fillKeys(args);
            search.fillParam();

            String absolutePathLogTXT = String.format
                    ("%s%s%s", search.getDirectory(), "/", search.getLogTXT());
            try (FileWriter fw = new FileWriter(absolutePathLogTXT)) {

                search.search(new File(search.getDirectory()), fw);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** point of entry
     * @param args
     */
    public static void main(String[] args) {
        new Find().init(args);
    }

}
