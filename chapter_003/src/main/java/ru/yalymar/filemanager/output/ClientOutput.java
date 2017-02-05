package ru.yalymar.filemanager.output;

import ru.yalymar.filemanager.start.Server;

import java.io.*;
import java.nio.file.Path;

public class ClientOutput implements Output {

    @Override
    public void writeToClient(String str) {
        try(DataOutputStream out = new DataOutputStream
                (Server.getInstance().getServerSocket().getOutputStream())) {
            out.writeUTF(String.format("%s%s", str, System.getProperty("line.separator")));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendFile(Path path) {
        try {
            byte[] byteArray = new byte[1024];
            FileInputStream fis = new FileInputStream(path.toFile());
            long s = path.toFile().length();
            BufferedOutputStream bos = new BufferedOutputStream(Server.getInstance().getServerSocket().getOutputStream());
            while (s > 0) {
                int i = fis.read(byteArray);
                bos.write(byteArray, 0, i);
                s-= i;
            }
            bos.flush();
            fis.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        } catch (IOException e) {
            System.err.println("IOException");
        } catch (Exception e) {

        }
    }

}
