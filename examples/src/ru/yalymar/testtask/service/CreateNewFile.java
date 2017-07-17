package ru.yalymar.testtask.service;

import java.io.File;
import java.util.Random;

/**
 * @author slavalymar
 * @since 17.07.2017
 * @version 1
 */
public interface CreateNewFile {

    /** create new tmp file
     * @param path
     * @param name
     * @param RANDOM
     * @return File
     */
    default File createNewFile(String path, String name, final Random RANDOM) {
        File file;
        do {
            file = new File(path, String.format("%s%s%s.txt", File.separator, name,
                    RANDOM.nextInt() * 1000));
        }
        while (file.exists());
        return file;
    }
}
