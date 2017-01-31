package ru.yalymar.consolechat;

import java.io.*;

/**
 * @author slavalymar
 * @since 31.01.2017
 * @version 1
 */
public class Start {

    /**
     * create ConsoleChat
     * write logic of app
     */
    public void init(){
        String pathLog = "C:\\Java\\junior\\chapter_003\\resources\\Task5\\log.txt";
        String pathAnswers = "C:\\Java\\junior\\chapter_003\\resources\\Task5\\Task5.txt";

        File log = new File(pathLog);
        String str = "закончить";

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader brAnswers = new BufferedReader(new FileReader(pathAnswers));
            BufferedWriter bwToFile = new BufferedWriter (new FileWriter(log))){

            ConsoleChat consoleChat = new ConsoleChat(brAnswers);
            do{
                str = consoleChat.toReadFromConsole(br);
                consoleChat.writeInFile(consoleChat.toWriteInConsole(str), bwToFile);
                if(consoleChat.isStop(str)) {
                    consoleChat.writeInFile(consoleChat.toAnswer(), bwToFile);
                }
            }
            while(!consoleChat.isExit(str));

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /** main method
     * @param args
     */
    public static void main(String[] args) {

        new Start().init();
    }
}
