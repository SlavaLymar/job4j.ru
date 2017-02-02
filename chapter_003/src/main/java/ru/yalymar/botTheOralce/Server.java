package ru.yalymar.botTheOralce;

import java.io.*;
import java.net.*;
import java.util.Scanner;


/**
 * @author slavalymar
 * @since 02.02.2017
 * @version 1
 */
public class Server {

    private final int port = 6666;
    private boolean exit = true;
    private int count = 0;

    /**
     * start server
     */
    public void startServer(){

        try(Socket socket =  new ServerSocket(port).accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream())){

            System.out.println("Server started");
            count++;
            String ask;
            do {
                System.out.println("wait command ...");
                ask = in.readUTF();
                System.out.println(ask);
                out.writeUTF(answer(ask));
                out.flush();
                if(answer(ask).equals("Bye bye. See you later.")) {
                    count--;
                    if(count == 0) exit();
                }
            }
            while(exit);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /** return answer
     * @param ask
     * @return String
     */
    private String answer(String ask){
        String tmp = "I can`t answer, `cause my English is very bad. Try ask something else";
        if (ask.toLowerCase().contains("hello")) {
            tmp = "Hello, dear friend, I'm a oracle.";
        }
        if(ask.toLowerCase().contains("?")){
            tmp = "It`s very interesting question. I will answer later.";
        }
        if(ask.toLowerCase().contains("!")){
            tmp = "You are so angry. Please, be quieter.";
        }
        if (ask.toLowerCase().contains("Vasya: exit")) {
            tmp = "Bye bye. See you later.";
        }
        return String.format("%s%s%s", "Oracle: ", tmp, System.getProperty("line.separator"));
    }

    /** ask exit if numbers of clients equals 0
     * @return boolean
     */
    private boolean exit(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to stop server? Y/N");
        String str = sc.nextLine();
        return str.toLowerCase().equals("y") ? (exit = false) : (exit = true);
    }
}
