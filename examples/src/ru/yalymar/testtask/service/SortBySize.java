package ru.yalymar.testtask.service;

import java.io.File;
import java.util.Arrays;

public interface SortBySize {

    default File[] sortBySize(String path){
        File[] files = new File(path).listFiles();

        if(files != null) {
            if (files.length > 1) {
                Arrays.sort(files, (o1, o2) -> {
                    long s1 = o1.length();
                    long s2 = o2.length();
                    return s1 == s2 ? 0 : s1 > s2 ? 1 : -1;
                });
            }
            return files;
        }
        else return null;
    }
}
