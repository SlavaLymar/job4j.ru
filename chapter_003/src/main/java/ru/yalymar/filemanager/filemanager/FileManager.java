package ru.yalymar.filemanager.filemanager;

import ru.yalymar.filemanager.exceptions.DontExistException;
import java.io.File;
import java.io.FileNotFoundException;

public class FileManager {

    private boolean stopSocket = true;

    public File[] getList(String s){
        String str = s.replace("dir", "");
        String[] listOfFiles = new File(str).list();
        File[] files = new File[listOfFiles.length];
        for(int i = 0; i<files.length; i++){
            files[i] = new File(listOfFiles[i]);
        }
        return files;
    }

    public String changeDirectory(String s) throws DontExistException {
        String str = s.replace("cd ", "");
        if(new File(str).exists()) {
            return str;
        }
        else throw new DontExistException("Directory is not exist!");
    }

    public String back(String s) throws DontExistException {
        System.out.println("s"+s);
        String str = s.replace("/cd..", "");
        String[] directories = str.split("/");
        String newString = "";
        for(int i = 0; i<directories.length-1; i++){
            newString = newString.concat(directories[i]).concat("/");
        }
        System.out.println("newString "+newString);
        if(new File(newString).exists()) {
            return newString;
        }
        else throw new DontExistException("Directory is not exist!");
    }

    public File download(String s) throws FileNotFoundException{
        String str = s.replaceAll("download ", "");
        if(new File(str).exists()){
            return new File(str);
        }
        else throw new FileNotFoundException("File is not found!");
    }

    public File[] upload(String s){
        File[] files = new File[2];
        int index = s.indexOf("upload ");

        String pathServerStr = s.substring(0, s.length()-(s.length()-index));
        files[0] = new File(pathServerStr);

        String pathClientStr = s.substring(index+7, s.length());
        files[1] = new File(pathClientStr);

        return files;
    }

    public void setStopSocket(boolean stopSocket) {
        this.stopSocket = stopSocket;
    }

    public boolean isStopSocket() {
        return this.stopSocket;
    }
}
