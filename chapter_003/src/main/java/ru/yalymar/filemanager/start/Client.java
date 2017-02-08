package ru.yalymar.filemanager.start;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 5000;
    private Socket socket;
    private static Scanner sc;

    public Client(Socket socket) {
        this.socket = socket;
        this.sc = new Scanner(System.in);
    }

    public void startClient(){
        try{
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            writeHelp(in);
            String str;
            do{
                str = this.enter(out);
                if(str.toLowerCase().equals("dir") ||
                        str.toLowerCase().contains("cd") ||
                        str.toLowerCase().equals("cd..")){
                    dircdcdback(in);
                    continue;
                }
                if(str.toLowerCase().contains("download")){
                    download(in, out);
                    continue;
                }
                if(str.toLowerCase().contains("upload")){
                    upload(in, out);
                    continue;
                }
            }
            while(!str.equals("exit"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private void upload(DataInputStream in, DataOutputStream out) throws IOException {
        String str = in.readUTF();
        out.writeUTF(str);
        FileInputStream fis = new FileInputStream(str);
        byte[] buffer = new byte[1024*1024];
        long fileLength = str.length();
        int count;
        while (fileLength > 0) {
            count = fis.read(buffer);
            out.write(buffer, 0, count);
            fileLength -= count;
        }
       out.flush();
    }

    public void download(DataInputStream in, DataOutputStream out) throws IOException {
        String s = in.readUTF();
        String [] strings = s.split("/");
        String fileName = strings[strings.length-1];
        int fileLength = in.read();
        File file = new File("C:/Java/junior/examples/resources", fileName);
        try(FileOutputStream fos = new FileOutputStream(file)){
            byte[] buffer = new byte[1024*1024];
            int count;
            while(fileLength>0){
                count = in.read(buffer);
                fos.write(buffer, 0, count);
                fileLength -= count;
            }
        }
    }

    public void writeHelp(DataInputStream in) throws IOException{
        for(int i = 1; i<9; i++) {
            System.out.println(in.readUTF());
        }
    }

    public void dircdcdback(DataInputStream in) throws IOException {
        String str = in.readUTF();
        System.out.println(str);
    }

    public String enter(DataOutputStream out) throws IOException {
        String enter = sc.nextLine();
        out.writeUTF(enter);
        return enter;
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
