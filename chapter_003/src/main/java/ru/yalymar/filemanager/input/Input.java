package ru.yalymar.filemanager.input;

import java.nio.file.Path;

public interface Input {

    String readFromClient();

    void getFile(Path path);
}
