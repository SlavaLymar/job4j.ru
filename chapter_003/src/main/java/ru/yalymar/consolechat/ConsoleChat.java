package ru.yalymar.consolechat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @since 31.01.2017
 * @version 1
 */
public class ConsoleChat {

    /**
     * list of answers. Answers in txt file
     */
    private List<String> listOfAnswers;

    /**
     * indicate for stop app
     */
    private boolean stop = true;

    /** constructor ConsoleChat. Init list of answers
     * @param brAnswers
     * @throws IOException
     */
    public ConsoleChat(BufferedReader brAnswers)throws IOException{
        this.listOfAnswers = fillListOfAnswers(brAnswers);
    }

    /** get list of answers
     * @return List
     */
    public List<String> getListOfAnswers() {
        return listOfAnswers;
    }

    /** set stop value
     * @param stop
     */
    public void setStop(boolean stop) {
        this.stop = stop;
    }

    /** fill list of answers. Answers in txt file
     * @param brAnswers
     * @return List
     * @throws IOException
     */
    public List<String> fillListOfAnswers(BufferedReader brAnswers) throws IOException{
        List <String> result = new ArrayList<>();
        String str = null;

        while((str = brAnswers.readLine()) != null){
            result.add(str);
        }
        return result;
    }

    /** read from console
     * @param br
     * @return String
     * @throws IOException
     */
    public String toReadFromConsole(BufferedReader br) throws IOException{
        return br.readLine();
    }

    /** AI answer
     * @return String
     */
    public String toAnswer() {

        return getAnswer();
    }

    /** get answer in list of answers
     * @return String
     */
    public String getAnswer() {
        String str = null;
        int listOfAnswersSize = listOfAnswers.size();
        int i = (int) (Math.random() * listOfAnswersSize);
        str = listOfAnswers.get(i);
        String result = "AI: "+str;
        System.out.println(result);
        return result;
    }

    /** write user`s enter on console
     * @param str
     * @return String
     */
    public String toWriteInConsole(String str) {
        String result = "User: "+str;
        System.out.println(result);
        return result;
    }

    /** method for stop and continue app
     * @param str
     * @return boolean
     */
    public boolean isStop(String str){
        if(str.toLowerCase().equals("стоп")) setStop(false);
        if(str.toLowerCase().equals("продолжить")) setStop(true);
        return stop;
    }

    /** method for write message in file
     * @param str
     * @param bwToFile
     * @throws IOException
     */
    public void writeInFile(String str, BufferedWriter bwToFile) throws IOException{

        bwToFile.write(String.format("%s%s", str, System.getProperty("line.separator")));
    }

    /** method for end of app
     * @param str
     * @return boolean
     */
    public boolean isExit(String str){

        return str.toLowerCase().equals("закончить") ? true : false;
    }



}