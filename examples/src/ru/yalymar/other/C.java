package ru.yalymar.other;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class C {

    private static final int PORT = 1234;
    private static final String HOST = "localhost";
    private Socket socket;

    public C(Socket socket) {
        this.socket = socket;
    }

    public void start(){
        try(DataInputStream dis = new DataInputStream(this.socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(this.socket.getOutputStream())){

            String str = dis.readUTF();
            System.out.println(str);
            //str = dis.readUTF();
            //System.out.println(str);
            dos.writeUTF("Answer");
            //dos.writeUTF("Gogo");

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try(Socket socket = new Socket(InetAddress.getByName(HOST),PORT)){
            C client = new C(socket);
            client.start();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
