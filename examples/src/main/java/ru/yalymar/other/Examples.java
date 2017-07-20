package ru.yalymar.other;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.Arrays;

public class Examples {

    Socket socket;

    public Examples(Socket socket) {
        this.socket = socket;
    }

    public void socketTestOut(String str){
        try(DataOutputStream ds = new DataOutputStream(this.socket.getOutputStream())){

              ds.writeUTF(str);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public String socketTestIn() {
        try(DataInputStream ds = new DataInputStream(this.socket.getInputStream())){
            return ds.readUTF();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return "nothing";
    }

    public static void main(String[] args) throws FileNotFoundException{
        try(InputStream in = new FileInputStream("C:/Java/junior/chapter_003/resources/HeapSort.txt");
            InputStream in1 = new URL("https://www.yandex.ru/").openStream();
            BufferedReader inConsole = new BufferedReader(new InputStreamReader(System.in))){

            String str = inConsole.readLine();
            System.out.println("Entered: "+str);
            //readFullyByByte(in);
            //System.out.println("\n\n\n");
            //readFullyByArray(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFullyByByte(InputStream in)throws IOException {
        int ineByte;
        while ((ineByte = in.read()) != -1) System.out.print((char) ineByte);
    }

    public static void readFullyByArray(InputStream in) throws IOException{
        byte[] buffer = new byte[20];

        int count;
        while((count = in.read(buffer)) != -1){
                System.out.println("count = "+count+" , buff = "+ Arrays.toString(buffer)+" , str = "
                        + new String(buffer, 0, count, "UTF-8"));
        }
    }


}
