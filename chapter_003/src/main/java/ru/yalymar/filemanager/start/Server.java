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

/**
 * @author slavalymar
 * @since 05.02.2017
 * @version 1
 */
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

    /**
     * get property from app.property
     */
    private static void getProperties() {
        Properties properties = new Properties();
        try(FileInputStream fileInputStream = new FileInputStream
                ("C:/Java/junior/chapter_003/src/main/resources/app.property")){

            properties.load(fileInputStream);
            host = properties.getProperty("ip");
            port = properties.getProperty("port");
            currentPath = properties.getProperty("path");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * start server
     */
    public void startServer(){
        try {

            FileManager fileManager = new FileManager();
            Help help = new Help(this.input, this.output, fileManager, this.currentPath);
            help.greetings(); // send greetings to client
            help.fillHelp(); // fill actions
            help.showHelp(); // send available actions to client
            this.showDirectory(); // send start directory to client

            do{
                help.select(this.input.readFromClient()); // select action
            }
            while(fileManager.isStopSocket()); // stop server
            this.serverSocket.close();
        }
        catch(IOException e){
            e.printStackTrace();
        } catch (DontExistException e) { 
            e.printStackTrace();
        }
    }

    /**
     * show start directory to client
     */
    public void showDirectory(){
        this.output.sendConsole(this.currentPath);
    }

    /** point of entry
     * @param args
     */
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
