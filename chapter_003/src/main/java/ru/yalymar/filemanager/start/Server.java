package ru.yalymar.filemanager.start;

import ru.yalymar.filemanager.filemanager.FileManager;
import ru.yalymar.filemanager.help.Help;
import ru.yalymar.filemanager.input.ClientInput;
import ru.yalymar.filemanager.input.Input;
import ru.yalymar.filemanager.output.ClientOutput;
import ru.yalymar.filemanager.output.Output;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Server {

    private Input input;
    private Output output;
    private String host;
    private String port;
    private Socket serverSocket;
    private static Server ourInstance = new Server();

    private Server(){
        this.input = new ClientInput();
        this.output = new ClientOutput();
        this.getProperties();
    }

    private void getProperties() {
        Properties properties = new Properties();
        try(FileInputStream fileInputStream = new FileInputStream
                ("C:/Java/junior/chapter_003/src/main/java/ru/yalymar/filemanager/resources/app.property")){

            properties.load(fileInputStream);
            this.host = properties.getProperty("ip");
            this.port = properties.getProperty("port");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static Server getInstance(){
        return ourInstance;
    }

    public void startServer(){
        try{
            this.serverSocket = new ServerSocket(Integer.valueOf(this.port)).accept();
            System.out.println("Server started");

            FileManager fileManager = new FileManager();
            Help help = new Help(this.input, this.output, fileManager);
            this.output.writeToClient(help.greetings());








        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public Socket getServerSocket() {
        return serverSocket;
    }
}
