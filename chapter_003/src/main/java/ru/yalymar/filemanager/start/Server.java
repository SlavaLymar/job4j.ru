package ru.yalymar.filemanager.start;

import ru.yalymar.filemanager.exceptions.DontExistException;
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
    private static String host;
    private static String port;
    private static Socket serverSocket;
    private static String currentPath;

    public Server(Socket socket){
        this.serverSocket = socket;
        this.input = new ClientInput(socket);
        this.output = new ClientOutput(socket);
    }

    private static void getProperties() {
        Properties properties = new Properties();
        try(FileInputStream fileInputStream = new FileInputStream
                ("C:/Java/junior/chapter_003/src/main/java/ru/yalymar/filemanager/resources/app.property")){

            properties.load(fileInputStream);
            host = properties.getProperty("ip");
            port = properties.getProperty("port");
            currentPath = properties.getProperty("path");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void startServer(){
        try {

            FileManager fileManager = new FileManager();
            Help help = new Help(this.input, this.output, fileManager, this.currentPath);
            help.greetings();
            help.fillHelp();
            help.showHelp();
            this.showDirectory();

            do{
                help.select(this.input.readFromClient());
            }
            while(fileManager.isStopSocket());
            this.serverSocket.close();
        }
        catch(IOException e){
            e.printStackTrace();
        } catch (DontExistException e) { 
            e.printStackTrace();
        }
    }

    public void showDirectory(){
        this.output.sendConsole(this.currentPath);
    }

    public static void main(String[] args) {
        getProperties();
        try(Socket serverSocket = new ServerSocket(Integer.valueOf(port)).accept()){

            Server server = new Server(serverSocket);
            server.startServer();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
