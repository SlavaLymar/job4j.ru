package ru.yalymar.filemanager.start;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @author slavalymar
 * @since 05.02.2017
 * @version 1
 */
public class Client {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 5000;
    private Socket socket;
    private static Scanner sc;
    private final String DIR = "dir";
    private final String CD = "cd";
    private final String CDBACK = "cd..";
    private final String DOWNLOAD = "download";
    private final String UPLOAD = "upload";
    private final String EXIT = "exit";

    public Client(Socket socket) {
        this.socket = socket;
        this.sc = new Scanner(System.in);
    }

    /**
     * start client
     */
    public void startClient(){
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            writeHelp(in); // write available actions
            String str;
            do{
                str = this.enter(out);
                if(DIR.equals(str.toLowerCase())){
                    dir(in);
                    continue;
                }
                if(CD.contains(str.toLowerCase()) ||
                        CDBACK.equals(str.toLowerCase())){
                    cdcdback(in);
                    continue;
                }
                if(DOWNLOAD.contains(str.toLowerCase())){
                    download(in, out);
                    continue;
                }
                if(UPLOAD.contains(str.toLowerCase())){
                    upload(in, out);
                    continue;
                }
            }
            while(!EXIT.equals(str.toLowerCase()));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /** send file to server
     * @param in
     * @param out
     * @throws IOException
     */
    private void upload(BufferedReader in, PrintWriter out) throws IOException {
        String str = in.readLine();
        out.println(str);
        FileInputStream fis = new FileInputStream(str);

        long fileLength = str.length();
        int count;
        while (fileLength > 0) {
            byte[] buffer = new byte[1024*1024];
            count = fis.read(buffer);

            // byte[] to char[]
            ByteBuffer buf = ByteBuffer.wrap(buffer);
            CharBuffer charbuf = Charset.forName("Cp866").decode(buf);
            char[] ch_array = charbuf.array();
            out.println(ch_array);
            fileLength -= count;
        }
       out.flush();
    }

    /** get file from server
     * @param in
     * @param out
     * @throws IOException
     */
    public void download(BufferedReader in, PrintWriter out) throws IOException {
        String s = in.readLine();
        String [] strings = s.split("/");
        String fileName = strings[strings.length-1];
        int fileLength = in.read();
        File file = new File("D:/dstr/job4j.ru/chapter_003/src/main/resources", fileName);
        try(FileOutputStream fos = new FileOutputStream(file)){

            int count;
            while(fileLength>0){
                char[] ch_array = new char[1024*1024];
                count = in.read(ch_array);

                //char[] to byte[]
                CharBuffer charBuffer = CharBuffer.wrap(ch_array);
                ByteBuffer byteBuffer = Charset.forName("Cp866").encode(charBuffer);
                byte[] buffer = byteBuffer.array();

                fos.write(buffer, 0, count);
                fileLength -= count;
            }
        }
    }

    public String getPath(){
        StringBuilder sb = new StringBuilder("");

        //Get file from resources
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource
                ("path.txt").getFile());

        try(Scanner scanner = new Scanner(file)){

            while(scanner.hasNext()){
                String line = scanner.nextLine();
                sb.append(line);
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * @param in
     * @throws IOException
     */
    public void writeHelp(BufferedReader in) throws IOException{
        for(int i = 1; i<9; i++) {
            System.out.println(in.readLine());
        }
    }

    /** get and write list of directory
     * @param in
     * @throws IOException
     */
    public void dir(BufferedReader in) throws IOException {
        String str;
        do{
            str = in.readLine();
            System.out.println(str);
        }
        while (in.ready());
    }

    /** get and write directory
     * @param in
     * @throws IOException
     */
    public void cdcdback(BufferedReader in) throws IOException {
        String str;
        do{
            str = in.readLine();
            System.out.println(str);
        }
        while (in.ready());
    }

    /** get enter from console and send to server
     * @param out
     * @return String
     * @throws IOException
     */
    public String enter(PrintWriter out) throws IOException {
        String enter = sc.nextLine();
        out.println(enter);
        return enter;
    }

    /** point of entry
     * @param args
     */
    public static void main(String[] args) {
        try(Socket socket = new Socket(InetAddress.getByName(HOST), PORT)){
            Client client = new Client(socket);
            client.startClient();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
