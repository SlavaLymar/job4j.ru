package ru.yalymar.filemanager.input;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class ClientInput implements Input {

    private Socket socket;

    public ClientInput(Socket socket) {
        this.socket = socket;
    }

    @Override
    public String readFromClient() {
        String str = "";
        try {
            BufferedReader in = new BufferedReader
                    (new InputStreamReader(socket.getInputStream()));
            str = in.readLine();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return str;
    }

    @Override
    public void getFile(String str) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String[] strings = in.readLine().split("/");
        String fileName = strings[strings.length-1];

        try(FileOutputStream fos = new FileOutputStream
                (str.concat(fileName))){

            int fileLength = in.read();
            int count;
            while (fileLength > 0) {
                char[] ch_array = new char[1024*1024];
                count = in.read(ch_array);

                CharBuffer charBuffer = CharBuffer.wrap(ch_array);
                ByteBuffer byteBuffer = Charset.forName("Cp866").encode(charBuffer);
                byte[] buffer = byteBuffer.array();
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
