package ru.yalymar.testtask.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author slavalymar
 * @since 17.07.2017
 * @version 1
 */
public interface SortBySize {

    /** count sorted temporary files in directory #path.
     * Tmp files must be named like "sort...."
     * @param path
     * @return File[]
     */
    default File[] sortBySize(String path){
        File[] files = new File(path).listFiles();
        List<File> list = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (!file.isDirectory()) {
                    if (file.getName().contains("sort")) {
                        list.add(file);
                    }
                }
            }
        }
        File[] result = new File[list.size()];
        list.toArray(result);

        if (result.length > 1) {
            Arrays.sort(result, (o1, o2) -> {
                long s1 = o1.length();
                long s2 = o2.length();
                return s1 == s2 ? 0 : s1 > s2 ? 1 : -1;
            });
        }
        return result;
    }
}
