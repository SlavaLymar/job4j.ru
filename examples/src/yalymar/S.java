package yalymar;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class S {

    private static final int PORT = 1234;
    private Socket socket;

    public S(Socket socket) {
        this.socket = socket;
    }

    public void start(){
        try(DataInputStream dis = new DataInputStream(this.socket.getInputStream())){

            Examples ex = new Examples(this.socket);
            ex.socketTestOut("hey");
            //ex.socketTestOut("heyhey");
            System.out.println(ex.socketTestIn());
            //System.out.println(ex.socketTestIn());
            //dos.writeUTF("hasjdhsjad");
            //String str = dis.readUTF();
            //System.out.println(str);
            //str = dis.readUTF();
            //System.out.println(str);
        }
        catch (IOException e){
                e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try(Socket socket = new ServerSocket(PORT).accept()){
            S server = new S(socket);
            server.start();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
