package ru.yalymar.testtask.service;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author slavalymar
 * @since 17.07.2017
 * @version 1
 */
public interface CountOfLines {

    /** return numerous of lines in the file
     * @param source
     * @return int
     * @throws IOException
     */
    default int countLines(RandomAccessFile source) throws IOException {
        int result = 0;
        while (source.readLine() != null) {
            result++;
        }
        return result;
    }
}
