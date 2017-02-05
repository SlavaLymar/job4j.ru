package ru.yalymar.filemanager.output;

import java.nio.file.Path;

public interface Output {

    void writeToClient(String str);

    void sendFile(Path path);
}
