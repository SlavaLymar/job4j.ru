package ru.yalymar.filemanager.filemanager;

import ru.yalymar.filemanager.exceptions.DontExistException;
import java.io.File;
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
            /*int index = str.indexOf("cd ");
            char[] resultChar = new char[str.length() - 3];
            char[] strChar = str.toCharArray();
            for (int i = 0; i < index; i++) {
                resultChar[i] = strChar[i];
            }
            for (int i = index + 3; i < str.length(); i++) {
                resultChar[i] = strChar[i];
            }
            */

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

    public Path download(Path path) throws DontExistException{
        String str = path.toString().replaceAll("download ", " ").trim();
        if(new File(str).exists()){
            return new File(str).toPath();
        }
        else throw new DontExistException("File is not exits!");
    }
}
