package ru.yalymar.piped;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedThreads {

    public static void main(String[] args) throws IOException{

        final PipedOutputStream out = new PipedOutputStream();
        final PipedInputStream in = new PipedInputStream();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    out.write("Hello thread2!!".getBytes());
                    out.flush();
                    out.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    int data;
                    while((data = in.read()) != -1){
                        String str = String.format("%3s%4s%s", data, " --> ", (char)data);
                        System.out.println(str);
                    }
                    in.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        in.connect(out);
        thread1.start();
        thread2.start();
    }
}
