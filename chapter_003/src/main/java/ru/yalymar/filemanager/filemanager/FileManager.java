package ru.yalymar.filemanager.filemanager;

import ru.yalymar.filemanager.exceptions.DontExistException;
import java.io.File;
import java.io.FileNotFoundException;

public class FileManager {

    public File[] getList(String s){
        String str = new File(s).toString().replaceAll("dir", "");
        String[] listOfFiles = new File(str).list();
        File[] files = new File[listOfFiles.length];
        for(int i = 0; i<files.length; i++){
            files[i] = new File(listOfFiles[i]);
        }
        return files;
    }

    public File changeDirectory(String s) throws DontExistException {
        String str = s.replaceAll("cd ", "");
        File file;
        if((file = new File(str)).exists()) {
            return file;
        }
        else throw new DontExistException("Directory is not exist!");
    }

    public File back(String s) throws DontExistException {
        String str = s.replaceAll("/cd..", "");
        String[] directories = str.split("/");
        String newString = "";
        for(int i = 0; i<directories.length-1; i++){
            newString = newString.concat(directories[i]).concat("/");
        }
        File file = new File(newString);
        if(file.exists()) {
            return file;
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
        File[] paths = new File[2];
        int index = s.indexOf("upload ");

        char[] pathServerChar = new char[s.length()];
        for(int i = 0; i<index; i++){
            s.toCharArray()[i] = pathServerChar[i];
        }
        String pathServerStr = new String(pathServerChar).trim();
        paths[0] = new File(pathServerStr);

        char[] pathClientChar = new char[s.length()];
        for(int i = index+7; i<s.length(); i++){
            s.toCharArray()[i] = pathClientChar[i];
        }
        String pathClientStr = new String(pathClientChar).trim();
        paths[1] = new File(pathClientStr);

        return paths;
    }


}
