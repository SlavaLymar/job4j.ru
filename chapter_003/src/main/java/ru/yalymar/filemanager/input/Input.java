package ru.yalymar.filemanager.input;

import java.io.File;

public interface Input {

    String readFromClient();

    void getFile(File file);
}
