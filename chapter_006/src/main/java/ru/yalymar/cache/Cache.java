package ru.yalymar.cache;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class Cache {

    private Map<File, SoftReference<String>> cache = new HashMap<>();
    private InputFile in = new InputFile();

    public String getObject(File file){
        GetCache gc = file1 -> {
            return this.cache.containsKey(file) ? this.cache.get(file).get() : null;
        };
        String result = gc.getCache(file);
        return result != null ? result : this.addToCache(file);
    }

    private String addToCache(File file){
        this.cache.put(file, new SoftReference<>(this.in.readFile(file)));
        return null;
    }

}

