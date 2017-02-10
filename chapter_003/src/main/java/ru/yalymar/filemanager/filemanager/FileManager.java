package ru.yalymar.filemanager.filemanager;

import ru.yalymar.filemanager.exceptions.DontExistException;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author slavalymar
 * @since 05.02.2017
 * @version 1
 */
public class FileManager {

    private boolean stopSocket = true;

    /** create directory
     * @param s
     * @return String[]
     */
    public String[] getList(String s){
        String str = s.replace("dir", "");
        String[] listOfFiles = new File(str).list();
        return listOfFiles;
    }

    /** create directory
     * @param s
     * @return String
     * @throws DontExistException
     */
    public String changeDirectory(String s) throws DontExistException {
        String str = s.replace("cd ", "");
        if(new File(str).exists()) {
            return str.concat("/");
        }
        else throw new DontExistException("Directory is not exist!");
    }

    /** create directory
     * @param s
     * @return String
     * @throws DontExistException
     */
    public String back(String s) throws DontExistException {
        String str = s.replace("/cd..", "");
        String[] directories = str.split("/");
        String newString = "";
        for(int i = 0; i<directories.length-1; i++){
            newString = newString.concat(directories[i]).concat("/");
        }
        if(new File(newString).exists()) {
            return newString;
        }
        else throw new DontExistException("Directory is not exist!");
    }

    /** create directory to download file
     * @param s
     * @return String
     * @throws FileNotFoundException
     */
    public String download(String s) throws FileNotFoundException{
        String str = s.replace("download ", "");
        if(new File(str).exists()){
            return str;
        }
        else throw new FileNotFoundException("File is not found!");
    }

    /** create directories. 1. Dir of server; 2. Dir+file of client
     * @param s
     * @return String[]
     */
    public String[] upload(String s){
        String[] files = new String [2];
        int index = s.indexOf("upload ");

        files[0] = s.substring(0, s.length()-(s.length()-index));
        files[1] = s.substring(index+7, s.length());
        return files;
    }

    /** set param
     * @param stopSocket
     */
    public void setStopSocket(boolean stopSocket) {
        this.stopSocket = stopSocket;
    }

    /** check exit
     * @return boolean
     */
    public boolean isStopSocket() {
        return this.stopSocket;
    }
}
