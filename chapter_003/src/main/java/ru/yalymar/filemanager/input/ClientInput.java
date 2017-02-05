package ru.yalymar.filemanager.input;

import ru.yalymar.filemanager.start.Server;

import java.io.DataInputStream;
import java.io.IOException;

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
}
