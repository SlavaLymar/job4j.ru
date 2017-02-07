package ru.yalymar.filemanager.output;

import java.io.File;

public interface Output {

    void writeToClient(String str);

    void sendFile(File file);

    void sendConsole(String str);
}
