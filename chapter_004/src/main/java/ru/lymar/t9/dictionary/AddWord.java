package ru.lymar.t9.dictionary;

import ru.lymar.t9.keyboard.Keyboard;

/**
 * @author slavalymar
 * @since 05.03.2017
 * @version 1
 */
public interface AddWord {

    /** add word to dictionary
     * @param key
     * @param word
     * @param keyboard
     * @return boolean
     */
    boolean addWord(String key, String word, Keyboard keyboard);
}
