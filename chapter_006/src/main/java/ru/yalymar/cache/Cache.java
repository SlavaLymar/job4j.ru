package ru.yalymar.cache;

import java.io.FileNotFoundException;
import java.lang.ref.SoftReference;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Cache {

    private Map<Path, SoftReference<String>> cache = new HashMap<>();

    public String getObject(Path path){
        GetCache gc = path1 -> {
            String result = String.valueOf(this.cache.get(path));
            return result;
        };
        String result = gc.getCache(path);
        return result != null ? result : this.addToCache(path);
    }

    private String addToCache(Path path){

    }


}

