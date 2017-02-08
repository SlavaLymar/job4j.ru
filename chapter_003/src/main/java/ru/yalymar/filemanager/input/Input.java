package ru.yalymar.filemanager.input;

import java.io.File;
import java.io.IOException;

public interface Input {

    String readFromClient();

    void getFile(String str) throws IOException;
}
