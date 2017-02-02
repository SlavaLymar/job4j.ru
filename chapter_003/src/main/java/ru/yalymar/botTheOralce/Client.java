package ru.yalymar.botTheOralce;

import java.net.*;
import java.io.*;

public class Client {

    private final String localhost = "127.0.0.1";
    private final int serverPort = 6666;

    public void startClient(){

        try(Socket socket = new Socket(InetAddress.getByName(localhost), serverPort);
            BufferedReader inConsole = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream())){

            String str;
            String string;

            out.writeUTF("Hello Oracle");
            System.out.println(in.readLine());
            do {
                string = inConsole.readLine();
                out.writeUTF(answer(string));
                System.out.println(answer(string));
                out.flush();
                if (!(str = in.readUTF()).isEmpty()) {
                    System.out.println(str);
                }
            } while(!string.toLowerCase().equals("exit"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private String answer(String str){
        return String.format("%s%s", "Vasya: ", str);
    }
}
