package ru.yalymar.filemanager.filemanager;

import ru.yalymar.filemanager.exceptions.DontExistException;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;

public class FileManager {

    public File[] getList(Path path){
        File[] listOfFiles = path.toFile().listFiles();
        File[] listOfRoots = path.toFile().listRoots();
        File[] result = new File[listOfFiles.length+listOfRoots.length];
        for(int i = 0; i<result.length; i++){
            result[i] = listOfFiles[i];
        }
        for(int i = listOfFiles.length; i<result.length; i++){
            result[i] = listOfRoots[i];
        }
        return result;
    }


    public Path changeDirectory(Path path) throws DontExistException {
        if(path.toFile().exists()) {
            String str = path.toString().replaceAll("cd ", " ").trim();
            return new File(str).toPath();
        }
        else throw new DontExistException("Directory is not exist!");
    }

    public Path back(Path path) throws DontExistException {
            String str = path.toString().replaceAll("/cd..", " ").trim();
            String[] directories = str.split("/");
            String newString = "";
            for(String s: directories){
                newString = newString.concat(s).concat("/");
            }
        File newPath = new File(newString);
        if(newPath.exists()) {
            return newPath.toPath();
        }
        else throw new DontExistException("Directory is not exist!");
    }

    public Path download(Path path) throws FileNotFoundException{
        String str = path.toString().replaceAll("download ", " ").trim();
        if(new File(str).exists()){
            return new File(str).toPath();
        }
        else throw new FileNotFoundException("File is not found!");
    }

    public Path[] upload(Path path){
        Path[] paths = new Path[2];
        int index = path.toString().indexOf("upload ");

        char[] pathServerChar = new char[path.toString().length()];
        for(int i = 0; i<index; i++){
            path.toString().toCharArray()[i] = pathServerChar[i];
        }
        String pathServerStr = new String(pathServerChar).trim();
        paths[0] = new File(pathServerStr).toPath();

        char[] pathClientChar = new char[path.toString().length()];
        for(int i = index+7; i<path.toString().length(); i++){
            path.toString().toCharArray()[i] = pathClientChar[i];
        }
        String pathClientStr = new String(pathClientChar).trim();
        paths[1] = new File(pathClientStr).toPath();

        return paths;
    }


}
