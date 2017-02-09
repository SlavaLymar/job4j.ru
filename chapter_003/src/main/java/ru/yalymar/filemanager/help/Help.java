package ru.yalymar.filemanager.help;

import ru.yalymar.filemanager.action.BaseAction;
import ru.yalymar.filemanager.action.ClientAction;
import ru.yalymar.filemanager.exceptions.DontExistException;
import ru.yalymar.filemanager.filemanager.FileManager;
import ru.yalymar.filemanager.output.Output;
import ru.yalymar.filemanager.input.Input;
import ru.yalymar.filemanager.start.Server;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Help {

    private Input input;
    private Output output;
    private FileManager fileManager;
    private final int arrLength = 6;
    private ClientAction[] clientActions = new ClientAction[arrLength];
    private int position = 0;
    private String currentPath;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm");

    public Help(Input input, Output output, FileManager fileManager, String currentPath) {
        this.input = input;
        this.output = output;
        this.fileManager = fileManager;
        this.currentPath = currentPath;
    }

    public void greetings(){
        this.output.writeToClient("Welcome to File Manager!");
    }

    public void fillHelp(){
        this.clientActions[position++] = new GetList("Get list. Write 'dir';");
        this.clientActions[position++] = new ChangeDirectory("Change directory. Write 'cd <DIR>';");
        this.clientActions[position++] = new Back("Back. Write 'cd..';");
        this.clientActions[position++] = new Download("Download from server. Write 'download <FILE>';");
        this.clientActions[position++] = new Upload("Upload to server. Write 'upload <ABSOLUTEDIR>/<FILE>';");
        this.clientActions[position++] = new Exit("Exit.");
    }

    public void select(String s) throws IOException, DontExistException {
        String str = String.format("%s%s",this.currentPath, s);
        if(str.toLowerCase().endsWith("/dir")){
            this.clientActions[0].execute(str, this.fileManager);
        }
        else if(str.toLowerCase().contains("/cd ")){
            this.clientActions[1].execute(str, this.fileManager);
        }
        else if(str.toLowerCase().endsWith("/cd..")){
            this.clientActions[2].execute(str, this.fileManager);
        }
        else if(str.toLowerCase().contains("/download ")){
            this.clientActions[3].execute(str, this.fileManager);
        }
        else if(str.toLowerCase().contains("/upload ")){
            this.clientActions[4].execute(str, this.fileManager);
        }
        else if(str.toLowerCase().endsWith("/exit")){
            this.clientActions[5].execute(str, this.fileManager);
        }
        else output.writeToClient("Wrong input!");
    }

    public void showHelp(){
        for(ClientAction clientAction: clientActions){
            if(clientAction!=null) {
                this.output.writeToClient(clientAction.print());
            }
        }
    }


    private class GetList extends BaseAction {

        public GetList(String name) {
            super(name);
        }

        @Override
        public int key() {
            final int k = 0;
            return k;
        }

        @Override
        public void execute(String s, FileManager fileManager) throws IOException {
            String[] dir = fileManager.getList(s);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm");
            String str = "";
            for (String f : dir) {
                String time = sdf.format(new Date(new File(f).lastModified()));

                str = String.format
                        ("%s%s%7s%10s%20s%s", str, time,
                               (new File(f).isFile() ? "   " : "<DIR>"),
                                new File(f).length(), new File(f).getName(),
                                System.getProperty("line.separator"));
            }
            output.writeToClient(String.format("%s%s", str, currentPath));
        }

    }


    private class ChangeDirectory extends BaseAction {

        public ChangeDirectory(String name) {
            super(name);
        }

        @Override
        public int key() {
            final int k = 1;
            return k;
        }

        @Override
        public void execute(String s, FileManager fileManager) throws IOException, DontExistException {
            String newPath = fileManager.changeDirectory(s);
            currentPath = newPath;
            output.writeToClient(currentPath);
        }

    }


    private class Back extends BaseAction {

        public Back(String name) {
            super(name);
        }

        @Override
        public int key() {
            final int k = 2;
            return 2;
        }

        @Override
        public void execute(String s, FileManager fileManager) throws IOException, DontExistException {
            String newPath = fileManager.back(s);
            currentPath = newPath;
            output.writeToClient(currentPath);
        }

    }


    private class Download extends BaseAction {

        public Download(String name) {
            super(name);
        }

        @Override
        public int key() {
            final int k = 3;
            return k;
        }

        @Override
        public void execute(String s, FileManager fileManager) throws IOException{
            String newPath = fileManager.download(s);
            output.sendFile(newPath);
        }
    }


    private class Upload extends BaseAction {

        public Upload(String name) {
            super(name);
        }

        @Override
        public int key() {
            final int k = 4;
            return k;
        }

        @Override
        public void execute(String s, FileManager fileManager) throws IOException, DontExistException {
            String[] paths = fileManager.upload(s);
            output.writeToClient(paths[1]);
            input.getFile(paths[0]);
        }
    }


    private class Exit extends BaseAction{

        public Exit(String name) {
            super(name);
        }

        @Override
        public int key() {
            int k = 6;
            return k;
        }

        @Override
        public void execute(String s, FileManager fileManager) throws IOException, DontExistException {
            fileManager.setStopSocket(false);
        }
    }



}

