package ru.yalymar.testtask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {

    private String directory;
    private String fileName;
    private String logTXT;

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

    public void select(File file, FileWriter fw) throws IOException {

            // -f
            if(this.keys[4].equals("-f")){
                new SearchByFullName().searchByFullName(fw, file);
            }
            // 'mask'
            else if(this.keys[4].equals("-m")){
                new SearchByMask().searchByMask(fw, file);
            }
            // "regex"
            else if(this.keys[4].equals("-r")){
                new SearchByRegex().searchByRegex(fw, file);
            }
            else {
                this.wrongKeyToSearch();
            }
            System.out.println(String.format
                    ("%s%s%s%s%s", "Info about search was added to ", this.directory, "/", this.logTXT,
                            System.getProperty("line.separator")));
        }



    private void wrongKeyToSearch() {
        System.out.println((String.format("%s%s%s%s%s%s%s",
                "Invalid keys for choose type of search!", System.getProperty("line.separator"),
                "-f - search for full name", System.getProperty("line.separator"),
                "-m - search for mask", System.getProperty("line.separator"),
                "-r - search for regex")));
    }

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

        public void searchByFullName(FileWriter fw, File file) throws IOException {

            if(keys[3].equals(file.getName())){
                String result = String.format("%s%s", file.getAbsoluteFile(), " was found!");
                System.out.println(result);
                fw.write(String.format("%s%s", result, System.getProperty("line.separator")));
                fw.flush();
                }
             else {
                String result = String.format("%s%s%s", file.getAbsoluteFile(), " isn`t equals ", keys[3]);
                System.out.println(result);
                fw.write(String.format("%s%s", result, System.getProperty("line.separator")));
                fw.flush();
            }
        }
    }

    private class SearchByMask{

        public void searchByMask(FileWriter fw, File file) throws IOException{

            PathMatcher matcher =
                    FileSystems.getDefault().getPathMatcher("glob:*"+keys[3]);
            Path filename = file.getAbsoluteFile().toPath();
            if(matcher.matches(filename)){
                String result = String.format
                        ("%s%s%s%s", file.getAbsoluteFile(), " was found with ", keys[3], " mask");
                System.out.println(result);
                fw.write(String.format("%s%s", result, System.getProperty("line.separator")));
                fw.flush();
            }
            else {
                String result = String.format
                        ("%s%s%s%s", file.getAbsoluteFile(), " isn`t suitable for ", keys[3], " mask");
                System.out.println(result);
                fw.write(String.format("%s%s", result, System.getProperty("line.separator")));
                fw.flush();
            }

        }
    }

    private class SearchByRegex{

        public void searchByRegex(FileWriter fw, File file) throws IOException{

            PathMatcher matcher =
                    FileSystems.getDefault().getPathMatcher("glob:*"+keys[3]);
            Path filename = file.getAbsoluteFile().toPath();

            if(){
                String result = String.format
                        ("%s%s%s%s", file.getAbsoluteFile(), " was found with ",
                                keys[3], " regular expression");
                System.out.println(result);
                fw.write(String.format("%s%s", result, System.getProperty("line.separator")));
                fw.flush();
            }
            else {
                String result = String.format
                        ("%s%s%s%s", file.getAbsoluteFile(), " isn`t suitable for ",
                                keys[3], " regular expression");
                System.out.println(result);
                fw.write(String.format("%s%s", result, System.getProperty("line.separator")));
                fw.flush();
            }

        }
    }


}
