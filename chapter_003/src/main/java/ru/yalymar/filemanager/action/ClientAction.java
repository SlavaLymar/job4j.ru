package ru.yalymar.filemanager.action;

import ru.yalymar.filemanager.exceptions.DontExistException;
import ru.yalymar.filemanager.filemanager.FileManager;
import java.io.File;
import java.io.IOException;

public interface ClientAction {

    int key();

    void execute(String str, FileManager fileManager) throws IOException, DontExistException;

    String print();
}
