package ru.yalymar.filemanager.filemanager;

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


    public Path changeDirectory(Path path){
        return path;
    }


}
