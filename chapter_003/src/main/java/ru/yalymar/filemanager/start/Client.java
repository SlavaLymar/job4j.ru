package ru.yalymar.filemanager.start;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private final String localhost = "127.0.0.1";
    private final int serverPort = 5000;
    private boolean exit = true;

    public void startClient(){
        try(Socket socket = new Socket(InetAddress.getByName(localhost), serverPort)){

            BufferedReader inConsole = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            String str;
            do{
                str = in.readUTF();
                System.out.println(str);
            }
            while(this.exit);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client().startClient();
    }
}
