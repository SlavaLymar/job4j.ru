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
    private int count = 0;  // users counter
    private Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    /**
     * start server
     */
    public void startServer(){

        try{

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Server started");
            count++;

            do {
                System.out.println("wait command ...");
                String ask = in.readLine();
                System.out.println(ask);
                out.println(answer(ask));
                if(answer(ask).contains("Bye bye. See you later.")) {
                    count--;
                    if(count == 0) exit();
                }
            }
            while(exit);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            close();
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
        if (ask.toLowerCase().contains("exit")) {
            tmp = "Bye bye. See you later.";
        }
        if(ask.toLowerCase().contains("fuck")){
            tmp = "Fuck you too.";
        }
        return String.format("%s%s", "Oracle: ", tmp);
    }

    /** ask exit if numbers of clients equals 0
     * @return boolean
     */
    private boolean exit(){
        //Scanner sc = new Scanner(System.in);
        //System.out.println("Do you want to stop server? Y/N");
        //String str = sc.nextLine();
        //if("y".equals(str.toLowerCase())){
            this.exit = false;
            return false;
        //}
        //else return true;
    }

    /**
     * close socket
     */
    private void close() {
        if(socket != null && !socket.isClosed()){
            try {
                socket.close();
            } catch (IOException ignore) {

            }
        }
    }

    public static void main(String[] args) {
        try (Socket socket = new ServerSocket(6666).accept()){
            Server server = new Server(socket);
            server.startServer();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
