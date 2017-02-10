package ru.yalymar.filemanager.input;

import java.io.IOException;

/**
 * @author slavalymar
 * @since 05.02.2017
 * @version 1
 */
public interface Input {

    /** read stream from client
     * @return String
     */
    String readFromClient();

    /** get file from client
     * @param str
     * @throws IOException
     */
    void getFile(String str) throws IOException;
}
