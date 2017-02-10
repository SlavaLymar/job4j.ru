package ru.yalymar.filemanager.action;

import ru.yalymar.filemanager.exceptions.DontExistException;
import ru.yalymar.filemanager.filemanager.FileManager;
import java.io.File;
import java.io.IOException;

/**
 * @author slavalymar
 * @since 05.02.2017
 * @version 1
 */
public interface ClientAction {

    int key();

    void execute(String str, FileManager fileManager) throws IOException, DontExistException;

    String print();
}
