package ru.yalymar.cache;

import java.io.File;

/**
 * @author slavalymar
 * @since 29.03.2017
 * @version 1
 */
public interface GetCache {

    /** get contains from cache
     * @param file
     * @return String
     */
    String getCache(File file);
}
