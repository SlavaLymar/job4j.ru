package ru.yalymar.testtask.service;

import java.io.File;
import java.util.Random;

public interface CreateNewFile {

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
