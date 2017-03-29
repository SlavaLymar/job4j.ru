package ru.yalymar.cache;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @author slavalymar
 * @since 29.03.2017
 * @version 1
 */
public class Cache {

    /**
     * cache
     */
    private Map<File, SoftReference<String>> cache = new HashMap<>();

    /**
     * file input
     */
    private InputFile in = new InputFile();

    /** get object from cache
     * @param file
     * @return String
     */
    public String getObject(File file){
        GetCache gc = file1 -> {
            return this.cache.containsKey(file) ? this.cache.get(file).get() : null;
        };
        String result = gc.getCache(file);

        // if file not found add contains into cache
        return result != null ? result : this.addToCache(file);
    }

    /**
     * @param file
     * @return String
     */
    private String addToCache(File file){
        this.cache.put(file, new SoftReference<>(this.in.readFile(file)));
        return null;
    }

}

