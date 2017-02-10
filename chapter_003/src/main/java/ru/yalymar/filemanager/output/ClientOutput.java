package ru.yalymar.filemanager.output;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * @author slavalymar
 * @since 05.02.2017
 * @version 1
 */
public class ClientOutput implements Output {

    private Socket socket;

    public ClientOutput(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void writeToClient(String str) {
        try{
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(str);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendFile(String str) {
        try{
            File file = new File(str);
            FileInputStream fis = new FileInputStream(file);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            long s = file.length();
            out.println(str);
            while (s > 0) {
                byte[] buffer = new byte[1024*1024];
                int i = fis.read(buffer);

                ByteBuffer buf = ByteBuffer.wrap(buffer);
                CharBuffer charbuf = Charset.forName("Cp866").decode(buf);
                char[] ch_array = charbuf.array();
                out.println(ch_array);
                s-= i;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendConsole(String str) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
