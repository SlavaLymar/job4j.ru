package ru.yalymar.filemanager.action;

import ru.yalymar.filemanager.filemanager.FileManager;

import java.io.IOException;
import java.nio.file.Path;

public interface ClientAction {

    int key();

    void execute(Path path, FileManager fileManager) throws IOException;

    String print();
}
