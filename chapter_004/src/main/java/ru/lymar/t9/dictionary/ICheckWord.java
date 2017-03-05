package ru.lymar.t9.dictionary;

import ru.lymar.t9.keyboard.Keyboard;

/**
 * @author slavalymar
 * @since 05.03.2017
 * @version 1
 */
public interface ICheckWord {

    /** check that the word matches the key
     * @param key
     * @param word
     * @param keyboard
     * @return boolean
     */
    boolean check(String key, String word, Keyboard keyboard);
}
