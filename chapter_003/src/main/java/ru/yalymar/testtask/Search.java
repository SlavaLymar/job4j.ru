package ru.yalymar.testtask;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {

    protected String directory;
    private String fileName;
    private String logTXT;

    /** keys
     * 0. -d
     * 1. c:/
     * 2. -n
     * 3. *.txt
     * 4. -"regex", f, 'mask'
     * 5. -o
     * 6. log.txt
     */
    private String[] keys;

    /**
     * fill this.directory, this.fileName, this.logTXT
     */
    public void fillParam(){

        this.directory = this.keys[1];
        this.fileName = this.keys[3];
        this.logTXT = this.keys[6];
    }

    /** fill this.keys
     * @param args
     */
    public void fillKeys(String[] args){

        this.keys = new String[args.length];
        for(int i = 0; i<this.keys.length; i++){
            this.keys[i] = args[i];
        }
    }

    public void select(){

        Pattern patternByMask = Pattern.compile("-['*.].+[']");
        Matcher matcherByMask = patternByMask.matcher(this.keys[4]);

        Pattern patternByRegex = Pattern.compile("-\".+[\"]");
        Matcher matcherByRegex = patternByRegex.matcher(this.keys[4]);
        // -f
        if(this.keys[4].equals("-f")){

        }
        // 'mask'
        else if(matcherByMask.find()){

        }
        // "regex"
        else if(matcherByRegex.find(){

        }
        else {

        }
    }

    /**
     * Getters
     */
    public String[] getKeys() {
        return keys;
    }

    public String getDirectory() {
        return directory;
    }

    public String getFileName() {
        return fileName;
    }

    public String getLogTXT() {
        return logTXT;
    }

    private class SearchByFullName{

        public SearchByFullName() {
        }

        public void search(String dir, String fileName, String log){
            String[] list = new File(dir).list();
            for(String str : list){
                if(str.equals(fileName)){
                    System.out.println(fileName+" was found!");
                    System.out.println("Info about search was added to "+log);
                }
            }
        }
    }

    private class SearchByMask{

        public void search(String dir, String fileName, String log){

        }
    }

    private class SearchByRegex{

        public void search(String dir, String fileName, String log){

        }
    }


}
