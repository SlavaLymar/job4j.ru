package ru.yalymar.testtask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author slavalymar
 * @since 12.02.2017
 * @version 1
 */
public class Search {

    private String directory;
    private String fileName;
    private String logTXT;
    private String flag;

    /** keys
     * 0. -d
     * 1. C:/Java
     * 2. -n
     * 3. 1.txt
     * 4. -r, f, -m
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
        this.flag = this.keys[4];
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

    /** select type of search
     * @param file
     * @param fw
     * @throws IOException
     */
    public void select(File file, FileWriter fw) throws IOException {

            // -f
            if(this.flag.equals("-f")){
                new SearchByFullName().searchByFullName(fw, file);
            }
            // 'mask'
            else if(this.flag.equals("-m")){
                new SearchByMask().searchByMask(fw, file);
            }
            // "regex"
            else if(this.flag.equals("-r")){
                new SearchByRegex().searchByRegex(fw, file);
            }
            else {
                this.wrongKeyToSearch();
            }
            System.out.println(String.format
                    ("%s%s%s%s%s", "Info about search was added to ", this.directory, "/", this.logTXT,
                            System.getProperty("line.separator")));
        }


    /**
     * write message for wrong key
     */
    private void wrongKeyToSearch() {
        System.out.println((String.format("%s%s%s%s%s%s%s",
                "Invalid keys for choose type of search!", System.getProperty("line.separator"),
                "-f - search for full name", System.getProperty("line.separator"),
                "-m - search for mask", System.getProperty("line.separator"),
                "-r - search for regex")));
    }

    /** search in current and sub directories
     * @param file
     * @param fw
     * @throws IOException
     */
    public void search(File file, FileWriter fw) throws IOException {

        File[] list = file.listFiles();
        for (File f : list) {
            if (f.isDirectory()) {
                search(f, fw);
            }
            else {
                this.select(f, fw);
            }
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

        /** search by full name
         * @param fw
         * @param file
         * @throws IOException
         */
        public void searchByFullName(FileWriter fw, File file) throws IOException {

            if(fileName.equals(file.getName())){
                String result = String.format("%s%s", file.getAbsoluteFile(), " was found!");
                System.out.println(result);
                fw.write(String.format("%s%s", result, System.getProperty("line.separator")));
                fw.flush();
                }
             else {
                String result = String.format("%s%s%s", file.getAbsoluteFile(), " isn`t equals ", fileName);
                System.out.println(result);
                fw.write(String.format("%s%s", result, System.getProperty("line.separator")));
                fw.flush();
            }
        }
    }

    private class SearchByMask{

        /** search by mask
         * @param fw
         * @param file
         * @throws IOException
         */
        public void searchByMask(FileWriter fw, File file) throws IOException{

            PathMatcher matcher =
                    FileSystems.getDefault().getPathMatcher("glob:*"+fileName);
            Path filename = file.getAbsoluteFile().toPath();
            if(matcher.matches(filename)){
                String result = String.format
                        ("%s%s%s%s", file.getAbsoluteFile(), " was found with ", fileName, " mask");
                System.out.println(result);
                fw.write(String.format("%s%s", result, System.getProperty("line.separator")));
                fw.flush();
            }
            else {
                String result = String.format
                        ("%s%s%s%s", file.getAbsoluteFile(), " isn`t suitable for ", fileName, " mask");
                System.out.println(result);
                fw.write(String.format("%s%s", result, System.getProperty("line.separator")));
                fw.flush();
            }

        }
    }

    private class SearchByRegex{

        /** search by regex
         * @param fw
         * @param file
         * @throws IOException
         */
        public void searchByRegex(FileWriter fw, File file) throws IOException{

            Pattern pattern = Pattern.compile(fileName);
            Matcher matcher = pattern.matcher(file.getAbsolutePath());

            if(matcher.find()){
                String result = String.format
                        ("%s%s%s%s", file.getAbsoluteFile(), " was found with ",
                                fileName, " regular expression");
                System.out.println(result);
                fw.write(String.format("%s%s", result, System.getProperty("line.separator")));
                fw.flush();
            }
            else {
                String result = String.format
                        ("%s%s%s%s", file.getAbsoluteFile(), " isn`t suitable for ",
                                fileName, " regular expression");
                System.out.println(result);
                fw.write(String.format("%s%s", result, System.getProperty("line.separator")));
                fw.flush();
            }

        }
    }


}
