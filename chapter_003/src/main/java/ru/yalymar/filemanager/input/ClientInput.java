package ru.yalymar.filemanager.input;

import ru.yalymar.filemanager.start.Server;

import java.io.*;
import java.nio.file.Path;

public class ClientInput implements Input {

    @Override
    public String readFromClient() {
        String str = null;
        try(DataInputStream in = new DataInputStream
                (Server.getInstance().getServerSocket().getInputStream())){
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
                            (new BufferedInputStream(Server.getInstance().getServerSocket().getInputStream()));
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
