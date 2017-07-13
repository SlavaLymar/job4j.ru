package ru.yalymar.testtask.service;

import java.io.File;

public interface DeleteFile {

    default void deleteFile(File tmpFile) {
        boolean bingo = false;
        do {
            bingo = tmpFile.delete();
        }
        while (!bingo);
    }
}
