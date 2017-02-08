package ru.yalymar.botTheOralce;

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * @author slavalymar
 * @since 02.02.2017
 * @version 1
 */
public class Client {

    private static final String localhost = "127.0.0.1";
    private static final int serverPort = 6666;
    private Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public Client(Socket socket) {
        this.socket = socket;
    }

    /**
     * start Client
     */
    public void startClient(){

        try{
            Scanner inConsole = new Scanner(System.in);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String str;
            String string;

            out.println("Hello Oracle");
            System.out.println(in.readLine());
            do {
                string = inConsole.next();
                out.println(answer(string));
                System.out.println(answer(string));
                if (!(str = in.readLine()).isEmpty()) {
                    System.out.println(str);
                }
            } while(!string.toLowerCase().equals("exit"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
    }

    /** create ask
     * @param str
     * @return String
     */
    private String answer(String str){

        return String.format("%s%s", "Vasya: ", str);
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

        try(Socket socket = new Socket(InetAddress.getByName(localhost), serverPort)){
            Client client = new Client(socket);
            client.startClient();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
