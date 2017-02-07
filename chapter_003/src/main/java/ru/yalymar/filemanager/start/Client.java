package ru.yalymar.filemanager.start;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 5000;
    private boolean exit = true;
    private Socket socket;
    private static Scanner sc;
    private String currentPath;

    public Client(Socket socket) {
        this.socket = socket;
        this.sc = new Scanner(System.in);
    }

    public void startClient(){
        try{
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            writeHelp(in);
            this.currentPath = in.readUTF();
            System.out.println(this.currentPath);
            do{
                this.enter(out);
                this.get(in);
            }
            while(this.exit);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void writeHelp(DataInputStream in) throws IOException{
        for(int i = 1; i<8; i++) {
            System.out.println(in.readUTF());
        }
    }

    public void get(DataInputStream in) throws IOException {
        String str = in.readUTF();
        System.out.println(str);
        while(in.readByte() != -1){
            if(in.readByte() == -1) break;
        }

    }

    public void enter(DataOutputStream out) throws IOException {
        System.out.println("Enter: ");
        String enter = sc.nextLine();
        out.writeUTF(enter);
        System.out.println("ender"+enter);
    }

    public static void main(String[] args) {
        try(Socket socket = new Socket(InetAddress.getByName(HOST), PORT)){
            Client client = new Client(socket);
            client.startClient();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
