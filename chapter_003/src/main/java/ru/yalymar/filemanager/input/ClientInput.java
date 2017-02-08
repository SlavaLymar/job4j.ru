package ru.yalymar.filemanager.input;

import java.io.*;
import java.net.Socket;

public class ClientInput implements Input {

    private Socket socket;

    public ClientInput(Socket socket) {
        this.socket = socket;
    }

    @Override
    public String readFromClient() {
        String str = "тут";
        try {
            DataInputStream in = new DataInputStream
                    (this.socket.getInputStream());
            str = in.readUTF();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return str;
    }

    @Override
    public void getFile(String str) throws IOException{
        DataInputStream bis =
                new DataInputStream
                        (this.socket.getInputStream());
        String[] strings = bis.readUTF().split("/");
        String fileName = strings[strings.length-1];

        try(FileOutputStream fos = new FileOutputStream
                (str.concat(fileName))){

            byte[] buffer = new byte[1024*1024];
            int fileLength = bis.read();
            int count;
            while (fileLength > 0) {
                count = bis.read(buffer);
                fos.write(buffer, 0, count);
                fileLength -= count;
            }
            fos.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
