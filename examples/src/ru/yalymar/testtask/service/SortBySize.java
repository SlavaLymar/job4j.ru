package ru.yalymar.testtask.service;

import java.io.File;
import java.util.Arrays;

public interface SortBySize {

    default String[] sortBySize(String path){
        String[] files = new File(path).list();
        if(files.length > 1){
            Arrays.sort(files, (o1, o2) -> {
                long s1 = new File(path, o1).length();
                long s2 = new File(path, o2).length();
                return s1 == s2 ? 0 : s1 > s2 ? 1 : -1;
            });
        }
        return files;
    }
}
