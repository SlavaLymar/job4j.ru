package ru.lymar.t9.dictionary;

import java.util.Map;

/**
 * @author slavalymar
 * @since 05.03.2017
 * @version 1
 */
public interface IDictionary extends AddWord{

    /** get dictionary
     * @return Map
     */
    Map <String, String> getDictionary();
}
