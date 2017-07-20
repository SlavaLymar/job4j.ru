package ru.yalymar.testtask.service;

import java.io.File;

/**
 * @author slavalymar
 * @since 17.07.2017
 * @version 1
 */
public interface DeleteFile {

    /** delete file
     * @param tmpFile
     */
    default void deleteFile(File tmpFile) {
        boolean bingo = false;
        do {
            bingo = tmpFile.delete();
        }
        while (!bingo);
    }
}
