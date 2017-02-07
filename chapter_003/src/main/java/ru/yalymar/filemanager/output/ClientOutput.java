package ru.yalymar.filemanager.output;

import java.io.*;
import java.net.Socket;

public class ClientOutput implements Output {

    private Socket socket;

    public ClientOutput(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void writeToClient(String str) {
        try{
            DataOutputStream out = new DataOutputStream
                    (this.socket.getOutputStream());
            out.writeUTF(str);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendFile(File file) {
        try(FileInputStream fis = new FileInputStream(file);
            BufferedOutputStream bos =
                    new BufferedOutputStream(this.socket.getOutputStream())){

            byte[] byteArray = new byte[1024];
            long s = file.length();
            while (s > 0) {
                int i = fis.read(byteArray);
                bos.write(byteArray, 0, i);
                s-= i;
            }
            bos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendConsole(String str) {
        try {
            DataOutputStream out = new DataOutputStream
                    (this.socket.getOutputStream());
            out.writeUTF(str);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
