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
    public void getFile(File file) {
        try(DataInputStream bis =
                    new DataInputStream
                            (new BufferedInputStream(this.socket.getInputStream()));
            FileOutputStream fos = new FileOutputStream(file)){

            byte[] buffer = new byte[1024*1024];
            long fileLength = Long.valueOf(bis.readUTF());
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
