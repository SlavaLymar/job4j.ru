package ru.yalymar.filemanager.output;

import ru.yalymar.filemanager.start.Server;

import java.io.DataOutputStream;
import java.io.IOException;

public class ClientOutput implements Output {

    @Override
    public void writeToClient(String str) {
        try(DataOutputStream out = new DataOutputStream
                (Server.getInstance().getServerSocket().getOutputStream())) {
            out.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
