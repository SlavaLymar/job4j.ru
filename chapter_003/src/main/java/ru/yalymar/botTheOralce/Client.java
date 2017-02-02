package ru.yalymar.botTheOralce;

import javax.xml.crypto.Data;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {

    private final String localhost = "127.0.0.1";
    private final int serverPort = 6666;

    public void startClient(){

        try(Socket socket = new Socket(InetAddress.getByName(localhost), serverPort);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            BufferedReader inConsole = new BufferedReader(new InputStreamReader(System.in))){

            String str;

            out.writeUTF("Hello Oracle!");
            do {
                String string = inConsole.readLine();
                out.writeUTF(answer(string));
                out.flush();
                System.out.println(answer(string));
                while (!(str = in.readUTF()).isEmpty()) {
                    System.out.println(str);
                }
            } while(!str.toLowerCase().equals("exit"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private String answer(String str){
        return String.format("%s%s%s", "Vasya: ", str, System.getProperty("line.separator"));
    }
}
